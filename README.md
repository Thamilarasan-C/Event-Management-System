# Event Management System

## Overview

The Event Management System is a platform designed to help event organizers to manage event, tickets and attendee by creating event and providing tickets.It will help the users to buy tickets for the attendees and the attendees can attend the event

## Technology Stack

- **Backend** Springboot
- **Database** MySQL

## Tables
 -1.User
 -2.Attendee
 -3.Event
 -4.TicketDetails
 -5.Registration
 -6.Ticket
 
## Features

### 1. User Registration

- **User Registration:** Allow  the users to register as a public user and organizer as their roles in our application.
- **Entity - User:** The registered users and their informations will be stored in the user table.
- **Work flow:**
  - 1.From SignUpRequest(dto) email, name , password(encoded)and roles are taken and saved it in a user table.
  - 2.Jwt token will get generated and jwt token is sent as LoginRespose(dto)
  
### 2. Authentication

- **Authenticate users** Authenticate users by their credentials and token will be provided.
- **Work flow:**
  - 1.From LoginRequest(dto) the credentials are authenticated with Userdetails in the user table
  - 2.jwt token will get generated and send this as a LoginResponse(dto)

### 3. Event Creation

- **Create and update Events:** Organizers can Easily create and set up new events with relevant details such as date, time, venue, and description. Organizers can update the event details.
- **Entity - Event,TicketDetails:** Created event details and ticket details are stored in the event and ticketDetails table.
- - **Work flow:**
  - 1.From EventInfo(dto) the event details are taken and saved in the event table
  - 2.Ticket details are stored in Ticket details table with eventId.
    
### 3. Event details

- **View event details and event posters** Users can see the Upcoming event posters.
- - **Work flow:**
  - 1.From Event table and ticket table ,the event details are taken and send list of EventPoster(dto) as a response.
    
### 4. Ticket Registration

- **Registration:** Allow  the users to register one or more attendee for event.
- **Ticketing:** Generate and manage electronic tickets with ticket token number which will bw used by the each attendee to attend the event.
- **Work flow:**
  - 1.From the ticketRegistration(dto) userId, eventId and list of attendees are taken.
  - 2.Save the attendee details in attendee table if not already available.
  - 3.Save the attendee count userId event Id in registraton table and get the regisdtation id.
  - 4.Find ticketDetailsId using uventId reduce the ticketAvailabls by attendeeCount.
  - 5.Send registrationId, ticketDetailsId , attendeesId and generate ticketTokens for each attendeee fo that event and save it in a ticket table.

### 5. Attendee management
- **View attendee details** Users can see the attendees they registered and organizers can see the attendees registered of their event.
- - **Work flow:**
  - 1.From Event table and ticket table ,the event details are taken and send list of EventPoster(dto) as a response.
