package com.myapp.mylibrary.directorychooser;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.view.MenuItem;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.myapp.mylibrary.preference.AppPreferences;
import com.myapp.mylibrary.appinfo.AppInfo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class UtilsApp {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_READ = 1;

    /**
     * Default folder where APKs will be saved
     * @return File with the path
     */
    public static File getDefaultAppFolder() {
        return new File(Environment.getExternalStorageDirectory() + "/Download/");
    }

    /**
     * Custom folder where APKs will be saved
     * @return File with the path
     */
    public static File getAppFolder(AppPreferences appPreferences) {
        //AppPreferences appPreferences =
         //       MLManagerApplication.getAppPreferences();
        return new File(appPreferences.getCustomPath(getDefaultAppFolder().getPath()));
    }


    public static Boolean copyFile(AppInfo appInfo, AppPreferences appPreferences) {
        Boolean res = false;

        File initialFile = new File(appInfo.getSource());
        File finalFile = getOutputFilename(appInfo,appPreferences);

        boolean ck = initialFile.exists();
        boolean ck2 = finalFile.exists();

        try {
            FileUtils.copyFile(initialFile, finalFile);
            res = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }


    /**
     * Retrieve the name of the extracted APK
     * @param appInfo AppInfo
     * @return String with the output name
     */
    public static String getAPKFilename(AppInfo appInfo, AppPreferences appPreferences) {
        //AppPreferences appPreferences = MLManagerApplication.getAppPreferences();
        String res;

        switch (appPreferences.getCustomFilename()) {
            case "1":
                res = appInfo.getAPK() + "_" + appInfo.getVersion();
                break;
            case "2":
                res = appInfo.getName() + "_" + appInfo.getVersion();
                break;
            case "4":
                res = appInfo.getName();
                break;
            default:
                res = appInfo.getAPK();
                break;
        }

        return res;
    }

    /**
     * Retrieve the name of the extracted APK with the path
     * @param appInfo AppInfo
     * @return File with the path and output name
     */
    public static File getOutputFilename(AppInfo appInfo, AppPreferences appPreferences) {
        return new File(getAppFolder(appPreferences).getPath() + "/" + getAPKFilename(appInfo,appPreferences) + ".apk");
    }

    /**
     * Delete all the extracted APKs
     * @return true if all files have been deleted, false otherwise
     */
    public static Boolean deleteAppFiles(AppPreferences appPreferences) {
        Boolean res = false;
        File f = getAppFolder(appPreferences);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
                file.delete();
            }
            if (f.listFiles().length == 0) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Opens Google Play if installed, if not opens browser
     * @param context Context
     * @param id PackageName on Google Play
     */
    public static void goToGooglePlay(Context context, String id) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + id)));
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + id)));
        }
    }

    /**
     * Opens Google Plus
     * @param context Context
     * @param id Name on Google Play
     */
    public static void goToGooglePlus(Context context, String id) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + id)));
    }

    /**
     * Retrieve your own app version
     * @param context Context
     * @return String with the app version
     */
    public static String getAppVersionName(Context context) {
        String res = "0.0.0.0";
        try {
            res = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Retrieve your own app version code
     * @param context Context
     * @return int with the app version code
     */
    public static int getAppVersionCode(Context context) {
        int res = 0;
        try {
            res = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public static Intent getShareIntent(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        intent.setType("application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }

    /**
     * Retrieve if an app has been marked as favorite
     * @param apk App to check
     * @param appFavorites Set with apps
     * @return true if the app is marked as favorite, false otherwise
     */
    public static Boolean isAppFavorite(String apk, Set<String> appFavorites) {
        Boolean res = false;
        if (appFavorites.contains(apk)) {
            res = true;
        }

        return res;
    }

    /**
     * Save the app as favorite
     * @param context Context
     * @param menuItem Item of the ActionBar
     * @param isFavorite true if the app is favorite, false otherwise
     */
    public static void setAppFavorite(Context context, MenuItem menuItem, Boolean isFavorite,
                                      int ic_star_white, int ic_star_border_white) {
        if (isFavorite) {
            menuItem.setIcon(ContextCompat.getDrawable(context, ic_star_white));
        } else {
            menuItem.setIcon(ContextCompat.getDrawable(context, ic_star_border_white));
        }
    }

    /**
     * Retrieve if an app is hidden
     * @param appInfo App to check
     * @param appHidden Set with apps
     * @return true if the app is hidden, false otherwise
     */
    public static Boolean isAppHidden(AppInfo appInfo, Set<String> appHidden) {
        Boolean res = false;
        if (appHidden.contains(appInfo.toString())) {
            res = true;
        }

        return res;
    }

    /**
     * Save the app as hidden
     * @param context Context
     * @param fabHide FAB button to change
     * @param isHidden true if the app is hidden, false otherwise
     */
    public static void setAppHidden(Context context, FloatingActionButton fabHide, Boolean isHidden,
                                    String action_unhide, String action_hide,int res_visibility, int res_off_white) {
        if (isHidden) {
            //fabHide.setTitle(context.getResources().getString(R.string.action_unhide));
            //fabHide.setIcon(R.drawable.ic_visibility_white);
            fabHide.setTitle(action_unhide);
            fabHide.setIcon(res_visibility);
        } else {
            //fabHide.setTitle(context.getResources().getString(R.string.action_hide));
            //fabHide.setIcon(R.drawable.ic_visibility_off_white);
            fabHide.setTitle(action_hide);
            fabHide.setIcon(res_off_white);
        }
    }

    /**
     * Save an app icon to cache folder
     * @param context Context
     * @param appInfo App to save icon
     * @return true if the icon has been saved, false otherwise
     */
    public static Boolean saveIconToCache(Context context, AppInfo appInfo) {
        Boolean res = false;

        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(appInfo.getAPK(), 0);
            File fileUri = new File(context.getCacheDir(), appInfo.getAPK());
            FileOutputStream out = new FileOutputStream(fileUri);
            Drawable icon = context.getPackageManager().getApplicationIcon(applicationInfo);
            BitmapDrawable iconBitmap = (BitmapDrawable) icon;
            iconBitmap.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, out);
            res = true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Delelete an app icon from cache folder
     * @param context Context
     * @param appInfo App to remove icon
     * @return true if the icon has been removed, false otherwise
     */
    public static Boolean removeIconFromCache(Context context, AppInfo appInfo) {
        File file = new File(context.getCacheDir(), appInfo.getAPK());
        return file.delete();
    }

    /**
     * Get an app icon from cache folder
     * @param context Context
     * @param appInfo App to get icon
     * @return Drawable with the app icon
     */
    public static Drawable getIconFromCache(Context context, AppInfo appInfo, Drawable ic_android) {
        Drawable res;

        try {
            File fileUri = new File(context.getCacheDir(), appInfo.getAPK());
            Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath());
            res = new BitmapDrawable(context.getResources(), bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            res = ic_android; //context.getResources().getDrawable(ic_android);
        }

        return res;
    }

    public static Boolean extractMLManagerPro(Context context, AppInfo appInfo,AppPreferences appPreferences, int res_banner) {
        Boolean res = false;
        File finalFile = new File(getAppFolder(appPreferences).getPath(), getAPKFilename(appInfo,appPreferences) + ".png");

        try {
            File fileUri = new File(context.getCacheDir(), getAPKFilename(appInfo,appPreferences) + ".png");
            FileOutputStream out = new FileOutputStream(fileUri);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res_banner); //R.drawable.banner_troll);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            FileUtils.moveFile(fileUri, finalFile);
            res = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static Boolean checkPermissions(Activity activity) {
        Boolean res = false;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_READ);

        } else {
            res = true;
        }

        return res;
    }

}
