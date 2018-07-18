package com.udacity.spyrakis.capstoneapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.udacity.spyrakis.capstoneapp.R;
import com.udacity.spyrakis.capstoneapp.activities.DetailsActivity;

/**
 * Created by pspyrakis on 18/7/18.
 */
public class PlaceAppWidgetProvider extends AppWidgetProvider {

    public static final String ACTIVITY_ACTION = "com.udacity.spyrakis.capstoneapp.ACTIVITY_ACTION";
    public static final String EXTRA_ITEM = "com.udacity.spyrakis.capstoneapp.EXTRA_ITEM";

    public static void sendRefreshBroadcast(Context context) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, PlaceAppWidgetProvider.class));
        context.sendBroadcast(intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int i = 0; i < appWidgetIds.length; ++i) {
            RemoteViews views = new RemoteViews(
                    context.getPackageName(),
                    R.layout.place_app_widget_provider
            );

            views.setTextViewText(R.id.widgetTitleLabel, context.getString(R.string.widget_title));

            Intent intent = new Intent(context, PlaceRemoteViewsService.class);
            views.setRemoteAdapter(R.id.widgetListView, intent);


            Intent startActivityIntent = new Intent(context, DetailsActivity.class);
            PendingIntent startActivityPendingIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.widgetListView, startActivityPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            // refresh all your widgets
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, PlaceAppWidgetProvider.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widgetListView);
        }
        super.onReceive(context, intent);
    }

}
