package com.belyaeva.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "chat")
public class Chat extends AbstractPersistable<Long> {

    @ManyToMany(mappedBy = "chats")
    List<User> users = new ArrayList<>();

    @OneToMany(cascade = ALL, mappedBy = "chat")
    List<Message> messages = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public Chat setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
