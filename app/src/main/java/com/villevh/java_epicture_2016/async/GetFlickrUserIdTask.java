



package com.villevh.java_epicture_2016.async;

        import android.os.AsyncTask;

        import com.googlecode.flickrjandroid.people.User;
        import com.villevh.java_epicture_2016.Global;
        import com.villevh.java_epicture_2016.MainActivity;

public class GetFlickrUserIdTask extends AsyncTask<Void, Integer, User> {
    private Global G;
    private User user;
    private MainActivity mActivity;

    public GetFlickrUserIdTask(Global G, User u, MainActivity ma) {
        super();
        this.G = G;
        this.user = u;
        this.mActivity = ma;
    }

    @Override
    protected User doInBackground(Void... params) {

        try {
            return (G.getF().getPeopleInterface().getInfo(this.user.getId()));
        }
        catch (Exception e) {
            return (null);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(User result) {
        this.mActivity.onUserDone(result);
    }
}