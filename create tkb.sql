
create Database database_cnpm2020;
USE database_cnpm2020;


CREATE TABLE config (
Name_TGB nvarchar(100) primary key,
Type_TGB nvarchar(100),
Has_Alarm_Clock nvarchar(100),
Is_Default_Display nvarchar(100) 
);
CREATE TABLE Define (
Type_TGB nvarchar(50) primary key,
SQL_Create nvarchar(500)
);

INSERT INTO Define (Type_TGB, SQL_Create) values (1,N'(
DayOfWeek nvarchar(100) not null,
NameSubject1 nvarchar(100),
NameSubject2 nvarchar(100),
NameSubject3 nvarchar(100),
NameSubject4 nvarchar(100),
NameSubject5 nvarchar(100),
IsMorning nvarchar(100) not null,
PRIMARY KEY (DayOfWeek,IsMorning)
)' ) ;
INSERT INTO Define (Type_TGB, SQL_Create ) values (2,N'(
DayOfWeek nvarchar(100) not null,
NameSubject1 nvarchar(100),
RoomSubject1 nvarchar(100),
NameSubject2 nvarchar(100),
RoomSubject2 nvarchar(100),
IsMorning nvarchar(100) not null,
PRIMARY KEY (DayOfWeek,IsMorning)
)
');
INSERT INTO Define (Type_TGB, SQL_Create) values (3, N'(
DayOfWeek nvarchar(100) not null,
NameSubject nvarchar(100),
TimeSubject nvarchar(100),
PRIMARY KEY (DayOfWeek,TimeSubject)
)');
