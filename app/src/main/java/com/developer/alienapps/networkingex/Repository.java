package com.developer.alienapps.networkingex;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AMIT on 24-Sep-17.
 */

public class Repository implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
    private String name;
    private Owner owner;

    public Repository() {

    }

    public Repository(String name, Owner owner) {
        setName(name);
        setOwner(owner);
    }

    public Repository(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        this.name = data[0];
        this.owner = new Owner(data[1]);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.name, this.owner.getLogin()});
    }

    public class Owner {

        private String login;

        public Owner() {

        }

        public Owner(String login) {
            setLogin(login);
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

    }
}
