package com.flow.client.model;

import com.flow.common.User;

public class ClientUser {

    public boolean checkUser(User user) {
        return new ClientConServer().sendLoginInfoToServer(user);
    }


}
