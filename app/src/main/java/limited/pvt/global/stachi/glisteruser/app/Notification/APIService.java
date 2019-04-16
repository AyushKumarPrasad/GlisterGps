package limited.pvt.global.stachi.glisteruser.app.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService
{
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAXyUnjvg:APA91bFq0zN5TChcg92MlXyNcKnLU3QeATK6UFaSskTqKYnVmrwVW4yTMyk9BFfNO8W1KnbhiM0HiQxfayydVkGw0EvEaMjk6K7gZv3kZe6r6OgI4DiD-4hofLMekQKQc0tjg0PXD4kx"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification (@Body Sender body);
}
