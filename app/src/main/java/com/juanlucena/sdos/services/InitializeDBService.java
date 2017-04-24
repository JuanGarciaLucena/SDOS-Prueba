package com.juanlucena.sdos.services;

import android.app.IntentService;
import android.content.Intent;

import com.juanlucena.sdos.model.User;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.List;

public class InitializeDBService extends IntentService{

    public static final String DB_INTIALIZED = "com.juanlucena.sdos.DB_INITIALIZED";

    public InitializeDBService() {
        super("InitializeDbService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        initiateDB();
    }

    private void initiateDB(){
        List<User> userList = new ArrayList<>();

        userList.add(new User(1, "admin", "admin", "Francisco José", "Pérez Leal", true, false, false, false, false, false, 0));
        userList.add(new User(2, "tech1", "tech1", "Guillem", "Ruíz García", false, true, false, true, false, true, 0));
        userList.add(new User(3, "tech2", "tech2", "Rubén", "Gimenez Alvarez", false, true, false, true, false, true, 0));
        userList.add(new User(4, "tech3", "tech3", "Arturo", "Cano Mendez", false, true, true, false, false, false, 0));
        userList.add(new User(5, "tech4", "tech4", "Martín", "Guerrero Ibañez", false, true, true, true, false, true, 5));
        userList.add(new User(6, "tech5", "tech5", "Yeray", "Pascual Torres", false, true, false, false, false, true, 0));
        userList.add(new User(7, "tech6", "tech6", "Mikel", "Casas Gonzalez", false, true, false, true, true, true, 0));
        userList.add(new User(8, "tech7", "tech7", "Marti", "Bosch Diaz", false, true, false, true, false, true, 0));

        for(User item: userList){
            SugarContext.init(getApplicationContext());
            item.save();
            SugarContext.terminate();
        }

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(InitializeDBService.DB_INTIALIZED);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(broadcastIntent);
    }
}
