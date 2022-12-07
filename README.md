# Warning:boom:!
This is my university project and there is no guarantee to the quality of the system, __please use for learning purpose only :mortar_board:__!

# Introduction
ThunderExchange is an exchange system build with mainstream technologies such as Spring cloud, Vertx, Disruptor etc. It features a gateway module which serves as the HTTP server for the front-end, a order collector and maching core which sits in a centralized location such as a colocation centre. 

The system consists of two parts, the regional part is spread across the globe and function as a regional gateway to accept orders that are nearest to client location, the colocation part is where the exchange sits. 

This two part design guarantees scalability, reliability and high performance in the following way:

- :family: Scalability : To handle more client, just add more gateways and order collectors.
- :baby_bottle: Reliability: Traffic surge resistance and very little service down-time enabled by __RAFT mechanism__. 
- :rocket: High performance: __Lockless multithreading__ with __native thread-safe data structure__ provides __blazing fast__:heart_eyes_cat: order matching. 

# System Structure
![Alt text](/bb.png?raw=true "Optional Title")
