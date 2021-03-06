package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession (ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login (String username, String password)  throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php"); //создается будущий запрос (пока пустой). Типо пост: тело и внутри параметры
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username)); // формирование набора параметров
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params)); // упаковка параметров и помещение в запрос  post.setEntity (запрос полностью сформирован)
        CloseableHttpResponse response = httpclient.execute(post); // отправка запроса
        String body = getTextFrom(response); // получаем ответ
        return body. contains(String.format("<span class=\"user-info\">%s</span>", username)); // проверка успешности входа пользователя, содержит ли код страницы нужные данные - должно быть имя пользователя
    }
    private String getTextFrom (CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        } // получение текста ответа
    }

    public boolean isLoggedInAs (String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpclient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    } // проверка какой пользователь сейчас залогинился в систему
}

//"<span class=\"italic\">%s</span>"