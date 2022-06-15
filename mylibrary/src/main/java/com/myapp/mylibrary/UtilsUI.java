package com.myapp.mylibrary;

import android.graphics.Color;

import com.mikepenz.materialdrawer.Drawer;

import java.util.Calendar;

public abstract class UtilsUI {
    public static int darker(int color,double factor){
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a,
                Math.max((int)(r*factor),0),
                Math.max((int)(g*factor),0),
                Math.max((int)(b*factor),0)
                );

    }

    public abstract Drawer setNavigationDrawer();
            /*Activity activity,
            final Context context, Toolbar toolbar,
            final FirstAdapter firstAdapter,final SecondAdapter secondAdapter,
            final ThirdAdapter thirdAdapter, final RecyclerView recyclerView*/
    //){

        /*String apps,system,hidden;

        final String loadingLabel = "...";
        int header;
        if(getDayorNight() == 1){
           header = R.drawable.header_day;
        }else{
            header = R.drawable.header_night;
        }

        if(firstAdapter != null){
            apps = Integer.toString(firstAdapter.getItemCount());
        }else{
            apps = loadingLabel;
        }
        if(secondAdapter != null){
            system = Integer.toString(secondAdapter.getItemCount());
        }else{
            system = loadingLabel;
        }
        if(thirdAdapter != null){
            hidden = Integer.toString(thirdAdapter.getItemCount());
        }else{
            hidden = loadingLabel;
        }
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(header)
                .build();
        Integer badgeColor = ContextCompat.getColor(context,R.color.divider);
        BadgeStyle badgeStyle = new BadgeStyle(badgeColor,badgeColor).withTextColor(Color.GRAY);

        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withActivity(activity);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.withAccountHeader(headerResult);

        //drawerBuilder.withStatusBarColor(UtilsUI.darker(Color.GREEN,0.8));

        drawerBuilder.addDrawerItems(
                new PrimaryDrawerItem().withName(
                        context.getResources().getString(R.string.action_apps)).
                        withIcon(GoogleMaterial.Icon.gmd_phone_android).withBadge(apps).withBadgeStyle(badgeStyle)
                        .withIdentifier(1),
                new PrimaryDrawerItem().withName(
                        context.getResources().getString(R.string.action_system_apps))
                        .withIcon(GoogleMaterial.Icon.gmd_android).withBadge(system).withBadgeStyle(badgeStyle)
                        .withIdentifier(2),
                new DividerDrawerItem(),
                new PrimaryDrawerItem().withName(
                        context.getResources().getString(R.string.action_favorites))
                        .withIcon(GoogleMaterial.Icon.gmd_star).withBadge(hidden).withBadgeStyle(badgeStyle)
                        .withIdentifier(3),
                new DividerDrawerItem(),
                new SecondaryDrawerItem().withName(
                        context.getResources().getString(R.string.action_buy))
                        .withIcon(GoogleMaterial.Icon.gmd_shop).withBadge(context.getResources().getString(R.string.action_buy_description))
                        .withSelectable(false).withIdentifier(5),
                new SecondaryDrawerItem().withName(
                        context.getResources().getString(R.string.action_setting))
                        .withIcon(GoogleMaterial.Icon.gmd_settings).withSelectable(false).withIdentifier(6),
                new SecondaryDrawerItem().withName(
                        context.getResources().getString(R.string.action_about))
                        .withIcon(GoogleMaterial.Icon.gmd_info).withSelectable(false).withIdentifier(7)
        );

        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch ((int) drawerItem.getIdentifier()){
                    case 1:
                        recyclerView.setAdapter(firstAdapter);
                        break;
                    case 2:
                        recyclerView.setAdapter(secondAdapter);
                        break;
                    case 3:
                        recyclerView.setAdapter(thirdAdapter);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        */
        //return drawerBuilder.build();
        //return null;
    //}

    private static int getDayorNight() {
        int actualHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if(actualHour >= 8 && actualHour < 19){
            return 1;
        }else{
            return 0;
        }
    }
}
