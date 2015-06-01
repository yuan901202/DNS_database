# DNS_database
A simple DNS database based on a in-memory/file-based B+ tree
My program only did the in-memory B+ tree.

## Description
Most large databases store their data in files, and access the data as needed. To make this computationally efficient, database management systems (DBMS) need to have indexes into the data to enable them to find a particular record without having to search through the whole database file. Relational DBMS's structure their data in the form of tables of records, where each record contains several fields describing a single entity. A table consists of a large set of records all of the same type.

Typically, the DBMS will have at least one index for each table - on the key field of the table. If the user might want to search for items based on other fields of the table, then the DBMS will have an index for each other such field.

A standard way of implementing an index for a database table is to use a B+ tree.

A Domain Name Server (DNS) is a service that will allow a user to find the IP address ( eg 130.195.6.22) of a given domain-name (eg bats.ecs.vuw.ac.nz), or the domain-names associated with an IP address. It is essentially a very simple DBMS that stores a single table database that maps between IP addresses and domain-names,

IP addresses are usually written as four 8 bit numbers (eg 130.195.5.12), but they can be viewed as a single 32 bit integer. Domain names, or Host names, are strings, which consist of a sequence of labels separated by dots. Each label can be up to 63 characters long, and the whole name can be up to 255 characters (including the dots). 
