package km.arfawy.android.ussd.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class Contact implements Parcelable{
    private int id;
    private String nom;
    private String prenom;
    private List<String> numbres;


    public Contact() {
       numbres = new ArrayList<String>();
    }

    public Contact(int id, String nom, String prenom) {
        this();
        numbres = new ArrayList<>();
        this.id = id;
        setNom(nom);
        setPrenom(prenom);
    }

    public Contact(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        prenom = in.readString();
        numbres = in.createStringArrayList();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getNom() {
        return nom;
    }
    public int getId() {
        return id;
    }
    public List<String> getNumbres() {
        return numbres;
    }
    public ArrayList<String> getArrayNumbres(){
        ArrayList<String> l = new ArrayList<>();
        for (int i = 0; i < numbres.size(); i++)
            l.add(numbres.get(i));
        return l;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        if(nom == null) nom = "";
        this.nom = nom;
    }
    public void setPrenom(String prenom){
        if(prenom == null) prenom = "";
        this.prenom = prenom;
    }
    public void setNumbres(List<String> numbres) {
        this.numbres = numbres;
    }

    public void add(String number){
        numbres.add(number);
    }


    public String toString() {
        return nom + " " + prenom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeStringList(numbres);
    }
}