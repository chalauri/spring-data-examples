package ge.chalauri.sdata.mongo;


import org.springframework.data.mongodb.core.index.TextIndexed;

/**
 * Created by Chalauri-G on 11/4/2017.
 */
public class AuthorDocument {

    @TextIndexed(weight = 3) //for text fields for full text search
    private String firstName;

    @TextIndexed(weight = 2) //for text fields for full text search
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
