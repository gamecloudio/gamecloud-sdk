package com.gamecloud.service;

import com.gamecloud.exception.GameCloudException;
import com.gamecloud.http.property.Property;
import com.gamecloud.http.request.DeleteRequest;
import com.gamecloud.http.request.PostRequest;
import com.gamecloud.http.request.RequestException;
import com.gamecloud.model.developer.Developer;
import com.gamecloud.utils.Utils;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class DeveloperService {

    public static void saveToCloud(Developer developer) throws GameCloudException {
        List<Property> parameters = Arrays.asList(new Property[]{
                new Property("developer_id", developer.getId()),
                new Property("password", developer.getPassword()),
                new Property("email", developer.getEmail())
        });
        try {
            URL url = new URL(Utils.ADDRESS + "/register_developer");
            PostRequest request = new PostRequest();
            Utils.check(request.request(url, parameters));
        } catch (RequestException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (IOException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (JSONException e) {
            throw new GameCloudException("Unrecognized response format: " + e.getMessage());
        }
    }

    public static String createGame(Developer developer, String gameName, String description) throws GameCloudException {
        List<Property> properties = Arrays.asList(new Property[]{
                new Property("developer_id", developer.getId()),
                new Property("password", developer.getPassword()),
                new Property("game_id", gameName),
                new Property("description", description)
        });
        try {
            URL url = new URL(Utils.ADDRESS + "/register_game");
            PostRequest request = new PostRequest();
            return Utils.checkReturnString(request.request(url, properties));
        } catch (RequestException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (IOException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (JSONException e) {
            throw new GameCloudException("Unrecognized response format: " + e.getMessage());
        }
    }

    public static void deleteGame(Developer developer, String gameKey) throws GameCloudException {
        List<Property> properties = Arrays.asList(new Property[]{
                new Property("developer_id", developer.getId()),
                new Property("password", developer.getPassword()),
                new Property("game_uuid", gameKey)
        });
        try {
            URL url = new URL(Utils.ADDRESS + "/delete_game");
            DeleteRequest request = new DeleteRequest();
            Utils.check(request.request(url, properties));
        } catch (RequestException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (IOException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new GameCloudException("Request failed: " + e.getMessage());
        } catch (JSONException e) {
            throw new GameCloudException("Unrecognized response format: " + e.getMessage());
        }
    }
}
