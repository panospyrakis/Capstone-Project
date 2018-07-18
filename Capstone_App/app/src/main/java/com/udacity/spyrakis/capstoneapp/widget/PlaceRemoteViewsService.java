package com.udacity.spyrakis.capstoneapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by pspyrakis on 18/7/18.
 */
public class PlaceRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}