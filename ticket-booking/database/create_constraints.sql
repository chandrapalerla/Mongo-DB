ALTER TABLE ticket_booking.events
    ADD CONSTRAINT pk_events PRIMARY KEY (id);
ALTER TABLE ticket_booking.events
    ADD CONSTRAINT uq_events_title_date UNIQUE (title, date);

ALTER TABLE ticket_booking.users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);
ALTER TABLE ticket_booking.users
    ADD CONSTRAINT uq_users_email UNIQUE (email);

ALTER TABLE ticket_booking.tickets
    ADD CONSTRAINT pk_tickets PRIMARY KEY (id);
ALTER TABLE ticket_booking.tickets
    ADD CONSTRAINT fk_tickets_users FOREIGN KEY (user_id) REFERENCES ticket_booking.users (id);
ALTER TABLE ticket_booking.tickets
    ADD CONSTRAINT fk_tickets_events FOREIGN KEY (event_id) REFERENCES ticket_booking.events (id);
ALTER TABLE ticket_booking.tickets
    ADD CONSTRAINT uq_tickets_event_id_place UNIQUE (event_id, place);
ALTER TABLE ticket_booking.tickets
    ADD CONSTRAINT uq_tickets_user_id_event_id_place UNIQUE (user_id, event_id, place);

ALTER TABLE ticket_booking.user_accounts
    ADD CONSTRAINT pk_user_accounts PRIMARY KEY (id);
ALTER TABLE ticket_booking.user_accounts
    ADD CONSTRAINT fk_user_accounts_users FOREIGN KEY (user_id) REFERENCES ticket_booking.users (id);
