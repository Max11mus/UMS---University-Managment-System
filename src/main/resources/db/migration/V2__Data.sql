
--- Groups
INSERT INTO ums.group_table
(group_id, group_name )
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'BackEnd Developers');
INSERT INTO ums.group_table
(group_id, group_name )
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'FrontEnd Developers');
INSERT INTO ums.group_table
(group_id, group_name )
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'DevOps Engineer');

--- students
INSERT INTO ums.student
(person_id, enroll_date, dropout_date, group_id, person_name, surname, birth_date, login, email, avatar_path, hashed_password, time_zone)
VALUES
('12611b1e-b277-4e64-8ff3-243a5d6fbc2d','2022-01-05','2022-07-07','91d2f442-fcd2-4d00-a23c-da207f75242d','Suzanne','Gallagher','1973-10-20','Suzi.Gallagher','Suzi.Gallagher@gmail.com','','16ee854def2c30aa8b225a79d2c27bffb3111ab326bcfdec6dd6adb44eeaf9ed','GMT-6:00'),
('f57e0ffe-6118-44a8-b39d-b2da86b65aff','2022-01-05','2022-07-07','91d2f442-fcd2-4d00-a23c-da207f75242d','Mary','Carpenter','1973-01-11','Mary_Carpenter','Mary_Carpenter@gmail.com','','588b72cc2bb56563a0d7c4724dd6684fea8e4446b6f625a48ec1d17eac113f03','GMT-8:00'),
('8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7','2022-01-05','2022-07-07','80242a81-0ec9-48a4-98ff-90dd7550caf1','Frank','Parker','1985-07-15','f_parker','f.parker@gmail.com','','ff241ff70d46c8c858d2bae7d25ce777ce7ad8224f0a26a7cf890d893a052c08','UTC'),
('b24d4f8d-f32f-4f88-a219-ebeb30568a1b','2022-01-05','2022-07-07','80242a81-0ec9-48a4-98ff-90dd7550caf1','Ivan','Moody','1980-01-07','Ivan_Moody','Ivan_Moody@gmail.com','','f9316e6d6ebe4f19475467382464fcac5acd8ec8f9343293067a4350d6a437ed','GMT-6:00'),
('c3e47148-adcf-4ee3-81f6-6b79b83a41ca','2022-01-05','2022-07-07','bc2322d6-046b-4b1e-a757-b5cf26eff20a','Kathy','Mizer','1977-08-06','Kathy$Mizer','Kathy$Mizer@gmail.com','','52fd199d620b29429bd44393a78a36b14ab1e607378488187f64e117f698e1d1','GMT+9:00');

--- Teachers
INSERT INTO ums.teacher
(person_id, academic_degree, employment_date, person_name, surname, birth_date, login, email, avatar_path, hashed_password, time_zone)
VALUES
('6e1e9867-4670-4520-8b85-7c195e72bd6c','Master of Science ','2021-08-04','Donna','Cohen','2003-05-07','d.cohen','d.cohen@gmail.com','','7f0485c93e4328b69b0b5f03a2b37cb73ef9838e9f3fdeb74c20474b1ea75e45','GMT+1:00'),
('210dd67b-7810-4edf-98be-e9a2cffe6290','Master of Science ','2021-08-04','Brian','Stafford','1993-01-03','b.stafford','s.stafford@gmail.com','','c24a4427638000149700d83eb40b84128028e9dfb570c410b6b002884067a0b8','GMT-5:00'),
('d87a90ba-1237-419a-b199-19dc389b4bbf','Master of Science ','2021-08-04','Richard ','Ortiz','1997-04-18','R_Ortiz','R_Ortiz@gmail.com','','ef2cb05f18494dbf2e27100f7ae03428b3ce7e7e7211d1c5e4c0db2259f70b8f','GMT-6:00');

--- Locations
INSERT INTO ums.location_table
(location_id, address)
VALUES('98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '626 Carter Haven Suite 802
North Joesphmouth, New Jersey, 45218
USA');
INSERT INTO ums.location_table
(location_id, address)
VALUES('e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '853 Upton Crossing Suite 560
East Andreannemouth, Illinois, 36125-7843
USA');
INSERT INTO ums.location_table
(location_id, address)
VALUES('81e5911e-ca54-4d72-82e4-224281563eab'::uuid, '180 Price Summit
New Jairoland, Texas, 17773
USA');
INSERT INTO ums.location_table
(location_id, address)
VALUES('28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '69497 Considine Isle Apt. 813
Handfurt, Virginia, 95523-7832
USA');

--- Subjects
INSERT INTO ums.subject
(subject_id, subject_name , description)
VALUES('2b41e5c2-76ce-46e1-895f-c5a6e588de64'::uuid, 'SQL (Structured Query Language)', 'SQL (Structured Query Language) is a domain-specific programming language designed for managing data held in a relational database management system (RDBMS).');
INSERT INTO ums.subject
(subject_id, subject_name , description)
VALUES('e6568e42-3a1b-415b-954a-4a13c1123fbd'::uuid, 'Java', 'Java is a general-purpose programming language that is class-based and object-oriented. The programming language is structured in such a way that developers can write code anywhere and run it anywhere without worrying about the underlying computer architecture. It is also referred to as write once, run anywhere (WORA).');
INSERT INTO ums.subject
(subject_id, subject_name , description)
VALUES('9284d65b-cf2a-4d51-8ab0-9f7ec370fc2a'::uuid, 'Javascript', 'Javascript (JS) is a scripting languages, primarily used on the Web. It is used to enhance HTML pages and is commonly found embedded in HTML code. JavaScript is an interpreted language. Thus, it doesn''t need to be compiled. JavaScript renders web pages in an interactive and dynamic fashion.');
INSERT INTO ums.subject
(subject_id, subject_name , description)
VALUES('85ce8809-8018-4fa6-9614-4d2568e88190'::uuid, 'Docker', 'Docker is an open source containerization platform. It enables developers to package applications into containers—standardized executable components combining application source code with the operating system (OS) libraries and dependencies required to run that code in any environment.');
INSERT INTO ums.subject
(subject_id, subject_name , description)
VALUES('bc18da4d-d496-4aac-8010-d23b3ea43dc5'::uuid, 'CI/CD', 'A continuous integration and continuous deployment (CI/CD) pipeline is a series of steps that must be performed in order to deliver a new version of software. CI/CD pipelines are a practice focused on improving software delivery throughout the software development life cycle via automation.');
INSERT INTO ums.subject
(subject_id, subject_name , description)
VALUES('4dabb352-5919-4727-8b05-f9afe2001a22'::uuid, 'HTML (HyperText Markup Language)', 'HTML, in full hypertext markup language, a formatting system for displaying material retrieved over the Internet. Each retrieval unit is known as a Web page (from World Wide Web), and such pages frequently contain hypertext links that allow related pages to be retrieved.');

--- Lectures
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('22b4b0c8-a48d-4f1a-a8aa-a6335da452ed'::uuid, 'Object Oriented Design in Java', 'Object-oriented design (OOD) is the process of using an object-oriented methodology to design a computing system or application. This technique enables the implementation of a software solution based on the concepts of objects. OOD serves as part of the object-oriented programming (OOP) process or lifecycle.', '210dd67b-7810-4edf-98be-e9a2cffe6290'::uuid, 'e6568e42-3a1b-415b-954a-4a13c1123fbd'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('4b0c8869-989c-4131-8b93-36a224886b9a'::uuid, 'Generics in Java', 'Generics are a facility of generic programming that were added to the Java programming language in 2004 within version J2SE 5.0. They were designed to extend Java''s type system to allow "a type or method to operate on objects of various types while providing compile-time type safety".', '6e1e9867-4670-4520-8b85-7c195e72bd6c'::uuid, 'e6568e42-3a1b-415b-954a-4a13c1123fbd'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('c7f1fb3f-7b51-4faa-9a7a-bf6a539d80f7'::uuid, 'Java Annotations', 'Java annotations are metadata (data about data) for our program source code. They provide additional information about the program to the compiler but are not part of the program itself. These annotations do not affect the execution of the compiled program. Annotations start with @ .', 'd87a90ba-1237-419a-b199-19dc389b4bbf'::uuid, 'e6568e42-3a1b-415b-954a-4a13c1123fbd'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('cabe1792-1859-4a7f-aceb-50602d8a393d'::uuid, ' SQL Commands', 'Types of SQL Commands:
DDL (Data Definition Language)
DML (Data Manipulation Language)
DQL (Data Query Language)
DCL (Data Control Language)
Data administration commands.
Transactional control commands.', '210dd67b-7810-4edf-98be-e9a2cffe6290'::uuid, '2b41e5c2-76ce-46e1-895f-c5a6e588de64'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('22bfa2c0-9022-49b6-ac34-c46cffe9677e'::uuid, 'PostgreSQL', 'PostgreSQL is a powerful, open source object-relational database system that uses and extends the SQL language combined with many features that safely store and scale the most complicated data workloads. The origins of PostgreSQL date back to 1986 as part of the POSTGRES project at the University of California at Berkeley and has more than 30 years of active development on the core platform.', '6e1e9867-4670-4520-8b85-7c195e72bd6c'::uuid, '2b41e5c2-76ce-46e1-895f-c5a6e588de64'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('f7814fc9-308c-4de8-9376-f9eb89f71c3b'::uuid, 'ACID', 'ACID (atomicity, consistency, isolation, and durability) is an acronym and mnemonic device for learning and remembering the four primary attributes ensured to any transaction by a transaction manager (which is also called a transaction monitor). These attributes are:
Atomicity. In a transaction involving two or more discrete pieces of information, either all of the pieces are committed or none are.
Consistency. A transaction either creates a new and valid state of data, or, if any failure occurs, returns all data to its state before the transaction was started.
Isolation. A transaction in process and not yet committed must remain isolated from any other transaction.
Durability. Committed data is saved by the system such that, even in the event of a failure and system restart, the data is available in its correct state.', 'd87a90ba-1237-419a-b199-19dc389b4bbf'::uuid, '2b41e5c2-76ce-46e1-895f-c5a6e588de64'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('647c440e-3973-4855-9849-d80d587d6c6b'::uuid, 'HTML tags', 'HTML tags are like keywords which defines that how web browser will format and display the content. With the help of tags, a web browser can distinguish between an HTML content and a simple content. HTML tags contain three main parts: opening tag, content and closing tag. But some HTML tags are unclosed tags.', '210dd67b-7810-4edf-98be-e9a2cffe6290'::uuid, '4dabb352-5919-4727-8b05-f9afe2001a22'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('b733b94c-5c59-4c3d-a04f-c308afc8e237'::uuid, 'Cascading Style Sheets (CSS) ', 'Cascading Style Sheets (CSS) is a stylesheet language used to describe the presentation of a document written in HTML or XML (including XML dialects such as SVG, MathML or XHTML). CSS describes how elements should be rendered on screen, on paper, in speech, or on other media.', '6e1e9867-4670-4520-8b85-7c195e72bd6c'::uuid, '4dabb352-5919-4727-8b05-f9afe2001a22'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('b1f7401e-d295-4dbc-9be9-7aed9e45354a'::uuid, 'Dynamic HTML', 'Dynamic HyerText Markup Language (DHTML) is a combination of Web development technologies used to create dynamically changing websites. Web pages may include animation, dynamic menus and text effects. The technologies used include a combination of HTML, JavaScript or VB Script,
CSS and the document object model (DOM).', 'd87a90ba-1237-419a-b199-19dc389b4bbf'::uuid, '4dabb352-5919-4727-8b05-f9afe2001a22'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('5b3f1141-770a-49a9-a986-f486f07e61c5'::uuid, 'Docker Architecture', 'Docker uses Client-Server architecture, which involves the 3 main components that are Docker Client, Docker Host, and Docker Registry. The Docker client communicates with the Docker daemon, which takes care of the building, running, and distributing the Docker containers. The Docker client and daemon can run on the same system or connect a client to a remote Docker daemon. They communicate using REST APIs, over UNIX sockets or a network interface.', '210dd67b-7810-4edf-98be-e9a2cffe6290'::uuid, '85ce8809-8018-4fa6-9614-4d2568e88190'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('2613a7b0-8b1e-4912-a7ee-e2f4de26fb1e'::uuid, 'Docker Images', 'Docker Image is a read-only (immutable) file that contains the source code, libraries, dependencies, tools, and other files needed for an application to run. To create a docker image, we write a Docker file with all of our requirements and perform a docker build command to get a Docker Image. This image is now ready to run as a Docker Container on successful creation.', '6e1e9867-4670-4520-8b85-7c195e72bd6c'::uuid, '85ce8809-8018-4fa6-9614-4d2568e88190'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('43850261-4d4a-40b3-8b57-9cb9592fdf88'::uuid, 'Docker Networking', 'Docker Networking connects the docker container and the outside world to communicate with Docker Host. The containers can be connected to non-Docker workloads. To add to that, Docker uses CNM Model for networking. The CNM model standardizes the steps needed to provide networking for containers utilizing multiple network drivers. The types of networking in Docker are listed below:
Bridge Networking
Host Networking
Overlay Networking
Macvlan Networking', 'd87a90ba-1237-419a-b199-19dc389b4bbf'::uuid, '85ce8809-8018-4fa6-9614-4d2568e88190'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('2fb88d50-f950-48d4-93ae-65807fc83968'::uuid, 'AngularJS', 'AngularJS is a structural framework for dynamic web apps. It lets you use HTML as your template language and lets you extend HTML''s syntax to express your application''s components clearly and succinctly. AngularJS''s data binding and dependency injection eliminate much of the code you would otherwise have to write.', '210dd67b-7810-4edf-98be-e9a2cffe6290'::uuid, '9284d65b-cf2a-4d51-8ab0-9f7ec370fc2a'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('a4374859-2941-4344-b074-5fc7656e3b0a'::uuid, 'React', 'React is a declarative, efficient, and flexible JavaScript library for building user interfaces. It lets you compose complex UIs from small and isolated pieces of code called “components”. React has a few different kinds of components, but we''ll start with React.Component subclasses: class ShoppingList extends React.', '6e1e9867-4670-4520-8b85-7c195e72bd6c'::uuid, '9284d65b-cf2a-4d51-8ab0-9f7ec370fc2a'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('db560055-5d3d-4499-a105-13b9c998b60e'::uuid, 'Vue.js', 'Vue.js is an open-source model–view–viewmodel front end JavaScript framework for building user interfaces and single-page applications.', 'd87a90ba-1237-419a-b199-19dc389b4bbf'::uuid, '9284d65b-cf2a-4d51-8ab0-9f7ec370fc2a'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('90a5dad5-706e-44ed-8c3c-279fd2371ea8'::uuid, 'Jenkins', 'Jenkins is a leading open-source CI server with plugin welfare for CI/CD purposes. Written in Java, it helps in building and testing software. It allows developers to integrate into various stages of the DevOps process cycle – building, documenting, testing, packaging, staging, deployment, static analysis and much more. Jenkins is a widely used tool with global support.', '210dd67b-7810-4edf-98be-e9a2cffe6290'::uuid, 'bc18da4d-d496-4aac-8010-d23b3ea43dc5'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, 'Bamboo', 'Bamboo is an automation server for continuous integration that automatically builds, integrates, tests the source code and prepares the application for deployment. It is a licensed tool that provides end-to-end visibility over the entire development, testing, and deployment process. It can be used with various tools, GUIs and allows the development team to implement continuous integration methodologies.', '6e1e9867-4670-4520-8b85-7c195e72bd6c'::uuid, 'bc18da4d-d496-4aac-8010-d23b3ea43dc5'::uuid);
INSERT INTO ums.lecture
(lecture_id, topic, description, teacher_id, subject_id)
VALUES('12c806e6-945c-4059-831a-cc9b6a708af0'::uuid, 'TeamCity', 'TeamCity is a CI server written in Java. It allows developers to integrate, code, and is easier to configure with simple steps. On TeamCity parallel builds run simultaneously on different platforms and environments. The robust set of out-of-the-box features and the plugin ecosystem are the key features of TeamCity. It is also a licensed tool that has a stunning UI.', 'd87a90ba-1237-419a-b199-19dc389b4bbf'::uuid, 'bc18da4d-d496-4aac-8010-d23b3ea43dc5'::uuid);

---Timetableunits
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('4ebaed1f-6234-4429-8d2d-89046c8ad1ae'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '22b4b0c8-a48d-4f1a-a8aa-a6335da452ed'::uuid, '2022-06-04 09:20:00.000', '2022-06-04 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('8a5eda93-a8ad-454b-add1-60a29495c0ff'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-04 09:20:00.000', '2022-06-04 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('0c6aa607-83d0-4c31-9903-d9ac1a3434e0'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, '2022-06-04 09:20:00.000', '2022-06-04 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('e8647e6b-e68f-40e3-9e25-b370a38bddfe'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '90a5dad5-706e-44ed-8c3c-279fd2371ea8'::uuid, '2022-06-05 09:20:00.000', '2022-06-05 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('0cc96b2a-224e-402f-82d0-4c56e684c2e5'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, 'b733b94c-5c59-4c3d-a04f-c308afc8e237'::uuid, '2022-08-03 09:20:00.000', '2022-08-03 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('19698d40-230e-4509-a94b-c4673f1269e5'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, '2022-06-05 09:20:00.000', '2022-06-05 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('33a71d82-8e97-4e5e-aca6-d9e94c85d33f'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, 'db560055-5d3d-4499-a105-13b9c998b60e'::uuid, '2022-08-03 09:20:00.000', '2022-08-03 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('5d4981d1-c0b2-47c8-8c90-0a4f0e418976'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '4b0c8869-989c-4131-8b93-36a224886b9a'::uuid, '2022-06-05 09:20:00.000', '2022-06-05 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('43be8d95-5cb8-4c07-9763-df18b2a8c124'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-08-03 09:20:00.000', '2022-08-03 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('f69b6ca6-96c3-4705-9527-6f8a63268331'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '4b0c8869-989c-4131-8b93-36a224886b9a'::uuid, '2022-06-05 9:20:00', '2022-06-05 11:20:00');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('3068a06b-cc96-4b6e-b1df-99a028b20cab'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, 'c7f1fb3f-7b51-4faa-9a7a-bf6a539d80f7'::uuid, '2022-08-03 09:20:00.000', '2022-08-03 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('339dc084-219f-454e-82dd-7aee895aae2f'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '2fb88d50-f950-48d4-93ae-65807fc83968'::uuid, '2022-06-05 9:20:00', '2022-06-05 11:20:00');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('639b9cbe-3654-40d6-ae7f-aaf651c85950'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, 'b1f7401e-d295-4dbc-9be9-7aed9e45354a'::uuid, '2022-06-05 9:20:00', '2022-06-05 11:20:00');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('9c1a0a40-b398-4c83-a571-1aaeb18e5414'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, '2022-06-05 9:20:00', '2022-06-05 11:20:00');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('65193c54-b3e2-4483-8f4f-b2d89f5f6397'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, 'c7f1fb3f-7b51-4faa-9a7a-bf6a539d80f7'::uuid, '2022-06-06 09:20:00.000', '2022-06-06 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('a6e7e3de-cb9b-459e-af1f-500a619000a3'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '22bfa2c0-9022-49b6-ac34-c46cffe9677e'::uuid, '2022-06-07 09:20:00.000', '2022-06-07 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('448e3dd5-704d-4635-9fd5-5bd5d5d2d6ef'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, '2022-06-08 09:20:00.000', '2022-06-08 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('e83c26d3-8402-4990-881b-a17737eaebe6'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, '2022-06-08 09:20:00.000', '2022-06-08 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('303e342c-9d05-4f8e-b118-916dbba7f78e'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, 'c7f1fb3f-7b51-4faa-9a7a-bf6a539d80f7'::uuid, '2022-06-08 09:20:00.000', '2022-06-08 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('826b603b-e40a-4126-8e92-c77e73150d51'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-08 09:20:00.000', '2022-06-08 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('ed23e77b-f22a-4705-98f8-ad879982b3f3'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, '2022-06-08 09:20:00.000', '2022-06-08 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('b88a472c-b167-4227-9ef1-1b6f364ecfaa'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '2613a7b0-8b1e-4912-a7ee-e2f4de26fb1e'::uuid, '2022-06-09 09:20:00.000', '2022-06-09 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('9001d675-3591-4971-a636-11aa64a7a25a'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '4b0c8869-989c-4131-8b93-36a224886b9a'::uuid, '2022-06-10 09:20:00.000', '2022-06-10 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('7cdf9f9b-08ff-42f4-8af6-127c8d802544'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '5b3f1141-770a-49a9-a986-f486f07e61c5'::uuid, '2022-06-10 09:20:00.000', '2022-06-10 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('15b604fb-402f-45ab-b76a-b3506e90a1ce'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-10 09:20:00.000', '2022-06-10 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('aafd1787-9bfb-4f43-90d8-a988686c400a'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-10 09:20:00.000', '2022-06-10 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('2135a1f8-0d8a-465c-9645-47698454a9b0'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '2fb88d50-f950-48d4-93ae-65807fc83968'::uuid, '2022-06-11 09:20:00.000', '2022-06-11 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('39bcb368-78d3-41dc-844a-e51f4515a5ea'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '90a5dad5-706e-44ed-8c3c-279fd2371ea8'::uuid, '2022-06-11 09:20:00.000', '2022-06-11 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('a2091806-e61d-48ce-be12-0ec5e7bf1254'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, 'a4374859-2941-4344-b074-5fc7656e3b0a'::uuid, '2022-06-11 09:20:00.000', '2022-06-11 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('19cc54ac-8faf-4e46-a52b-6f1306017c5b'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, 'f7814fc9-308c-4de8-9376-f9eb89f71c3b'::uuid, '2022-06-11 09:20:00.000', '2022-06-11 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('05f44661-ec6f-4c79-bfff-af6e0074645b'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-11 09:20:00.000', '2022-06-11 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('87290678-8937-453d-80d6-373cbe568144'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '43850261-4d4a-40b3-8b57-9cb9592fdf88'::uuid, '2022-06-12 09:20:00.000', '2022-06-12 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('8435c167-30b8-4f99-8fe5-4fafdbd68c6a'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-12 09:20:00.000', '2022-06-12 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('6e1f4990-9878-419e-964b-98e9b1b4c827'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '4b0c8869-989c-4131-8b93-36a224886b9a'::uuid, '2022-06-12 09:20:00.000', '2022-06-12 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('db139ff9-76e7-440f-b655-6f89d28ecb1d'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-12 09:20:00.000', '2022-06-12 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('8a2e3c2a-c7e8-4daf-a34d-777ad41c8d8f'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, 'c7f1fb3f-7b51-4faa-9a7a-bf6a539d80f7'::uuid, '2022-06-13 09:20:00.000', '2022-06-13 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('523e7da9-24b0-49f3-8a32-b65e0810420a'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '5b3f1141-770a-49a9-a986-f486f07e61c5'::uuid, '2022-06-14 09:20:00.000', '2022-06-14 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('b5d9b3bb-99de-4643-bfd3-6b90b0170416'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-06-20 09:20:00.000', '2022-06-20 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('8455193d-7d88-45e8-a1cd-9a995278ffe9'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '5b3f1141-770a-49a9-a986-f486f07e61c5'::uuid, '2022-07-01 09:20:00.000', '2022-07-01 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('85cc7c7a-359c-4bb0-b473-039b07582fa6'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '2613a7b0-8b1e-4912-a7ee-e2f4de26fb1e'::uuid, '2022-07-01 09:20:00.000', '2022-07-01 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('a0a6c500-8ea7-4839-97df-86d1a12bc497'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-07-01 09:20:00.000', '2022-07-01 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('59f07276-5934-4c15-b3bf-2085497814ee'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, 'cabe1792-1859-4a7f-aceb-50602d8a393d'::uuid, '2022-07-01 09:20:00.000', '2022-07-01 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('5be2c685-63e4-4c81-ab50-9d4bdf99262f'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, 'f7814fc9-308c-4de8-9376-f9eb89f71c3b'::uuid, '2022-07-01 09:20:00.000', '2022-07-01 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('2f729372-c008-4dd9-b114-a7a614d1bd18'::uuid, '28cc17b7-6539-4ca4-9e7a-4386616b0166'::uuid, '22bfa2c0-9022-49b6-ac34-c46cffe9677e'::uuid, '2022-07-07 09:20:00.000', '2022-07-07 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('6136f342-ad86-4c72-a2d8-40dd04934773'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-07-07 09:20:00.000', '2022-07-07 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('e4acd5ae-b928-4c3b-a41b-33ccf40a4e96'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, 'f7814fc9-308c-4de8-9376-f9eb89f71c3b'::uuid, '2022-07-07 09:20:00.000', '2022-07-07 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('f3aaaa26-813e-4c6c-bd62-d742fa542a74'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, 'f7814fc9-308c-4de8-9376-f9eb89f71c3b'::uuid, '2022-07-07 09:20:00.000', '2022-07-07 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('d0cea4cd-f6a7-4f90-830c-e0c5bb3e4841'::uuid, '81e5911e-ca54-4d72-82e4-224281563eab'::uuid, '1876ecb7-3388-4a97-9821-e7c4f8c5a2ff'::uuid, '2022-07-13 09:20:00.000', '2022-07-13 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('415986c4-5f95-4cef-a4fc-b0ec11401a74'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '90a5dad5-706e-44ed-8c3c-279fd2371ea8'::uuid, '2022-07-13 09:20:00.000', '2022-07-13 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('b0a9e198-cde1-4274-b1c9-910df4f9aec7'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-07-13 09:20:00.000', '2022-07-13 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('c9a67880-2767-4a2b-87fd-356dbcf60bea'::uuid, 'e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823'::uuid, '43850261-4d4a-40b3-8b57-9cb9592fdf88'::uuid, '2022-07-13 09:20:00.000', '2022-07-13 11:20:00.000');
INSERT INTO ums.time_table_unit
(time_table_unit_id, location_id, lecture_id, begin_time, end_time)
VALUES('7be88525-4117-4ddb-9678-ad9d3817d5d0'::uuid, '98f87c9b-87b6-4aac-a5df-debea92bf648'::uuid, '647c440e-3973-4855-9849-d80d587d6c6b'::uuid, '2022-07-13 09:20:00.000', '2022-07-13 11:20:00.000');

--- timetableunit_groups
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '0c6aa607-83d0-4c31-9903-d9ac1a3434e0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '4ebaed1f-6234-4429-8d2d-89046c8ad1ae'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '8a5eda93-a8ad-454b-add1-60a29495c0ff'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '5d4981d1-c0b2-47c8-8c90-0a4f0e418976'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'f69b6ca6-96c3-4705-9527-6f8a63268331'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'e8647e6b-e68f-40e3-9e25-b370a38bddfe'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '19698d40-230e-4509-a94b-c4673f1269e5'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '339dc084-219f-454e-82dd-7aee895aae2f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '639b9cbe-3654-40d6-ae7f-aaf651c85950'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '9c1a0a40-b398-4c83-a571-1aaeb18e5414'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '65193c54-b3e2-4483-8f4f-b2d89f5f6397'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'a6e7e3de-cb9b-459e-af1f-500a619000a3'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'e83c26d3-8402-4990-881b-a17737eaebe6'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '303e342c-9d05-4f8e-b118-916dbba7f78e'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '448e3dd5-704d-4635-9fd5-5bd5d5d2d6ef'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '826b603b-e40a-4126-8e92-c77e73150d51'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'ed23e77b-f22a-4705-98f8-ad879982b3f3'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'b88a472c-b167-4227-9ef1-1b6f364ecfaa'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '9001d675-3591-4971-a636-11aa64a7a25a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '7cdf9f9b-08ff-42f4-8af6-127c8d802544'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '15b604fb-402f-45ab-b76a-b3506e90a1ce'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'aafd1787-9bfb-4f43-90d8-a988686c400a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '2135a1f8-0d8a-465c-9645-47698454a9b0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '39bcb368-78d3-41dc-844a-e51f4515a5ea'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '05f44661-ec6f-4c79-bfff-af6e0074645b'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'a2091806-e61d-48ce-be12-0ec5e7bf1254'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '19cc54ac-8faf-4e46-a52b-6f1306017c5b'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'db139ff9-76e7-440f-b655-6f89d28ecb1d'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '87290678-8937-453d-80d6-373cbe568144'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '8435c167-30b8-4f99-8fe5-4fafdbd68c6a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '6e1f4990-9878-419e-964b-98e9b1b4c827'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '8a2e3c2a-c7e8-4daf-a34d-777ad41c8d8f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '523e7da9-24b0-49f3-8a32-b65e0810420a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'b5d9b3bb-99de-4643-bfd3-6b90b0170416'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '5be2c685-63e4-4c81-ab50-9d4bdf99262f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '8455193d-7d88-45e8-a1cd-9a995278ffe9'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '85cc7c7a-359c-4bb0-b473-039b07582fa6'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'a0a6c500-8ea7-4839-97df-86d1a12bc497'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '59f07276-5934-4c15-b3bf-2085497814ee'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'f3aaaa26-813e-4c6c-bd62-d742fa542a74'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '6136f342-ad86-4c72-a2d8-40dd04934773'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'e4acd5ae-b928-4c3b-a41b-33ccf40a4e96'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '2f729372-c008-4dd9-b114-a7a614d1bd18'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'c9a67880-2767-4a2b-87fd-356dbcf60bea'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '7be88525-4117-4ddb-9678-ad9d3817d5d0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'd0cea4cd-f6a7-4f90-830c-e0c5bb3e4841'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '415986c4-5f95-4cef-a4fc-b0ec11401a74'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, 'b0a9e198-cde1-4274-b1c9-910df4f9aec7'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '0cc96b2a-224e-402f-82d0-4c56e684c2e5'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '33a71d82-8e97-4e5e-aca6-d9e94c85d33f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '43be8d95-5cb8-4c07-9763-df18b2a8c124'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('80242a81-0ec9-48a4-98ff-90dd7550caf1'::uuid, '3068a06b-cc96-4b6e-b1df-99a028b20cab'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '0c6aa607-83d0-4c31-9903-d9ac1a3434e0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '4ebaed1f-6234-4429-8d2d-89046c8ad1ae'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '8a5eda93-a8ad-454b-add1-60a29495c0ff'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '5d4981d1-c0b2-47c8-8c90-0a4f0e418976'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'f69b6ca6-96c3-4705-9527-6f8a63268331'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'e8647e6b-e68f-40e3-9e25-b370a38bddfe'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '19698d40-230e-4509-a94b-c4673f1269e5'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '339dc084-219f-454e-82dd-7aee895aae2f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '639b9cbe-3654-40d6-ae7f-aaf651c85950'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '9c1a0a40-b398-4c83-a571-1aaeb18e5414'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '65193c54-b3e2-4483-8f4f-b2d89f5f6397'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'a6e7e3de-cb9b-459e-af1f-500a619000a3'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'e83c26d3-8402-4990-881b-a17737eaebe6'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '303e342c-9d05-4f8e-b118-916dbba7f78e'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '448e3dd5-704d-4635-9fd5-5bd5d5d2d6ef'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '826b603b-e40a-4126-8e92-c77e73150d51'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'ed23e77b-f22a-4705-98f8-ad879982b3f3'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'b88a472c-b167-4227-9ef1-1b6f364ecfaa'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '9001d675-3591-4971-a636-11aa64a7a25a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '7cdf9f9b-08ff-42f4-8af6-127c8d802544'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '15b604fb-402f-45ab-b76a-b3506e90a1ce'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'aafd1787-9bfb-4f43-90d8-a988686c400a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '2135a1f8-0d8a-465c-9645-47698454a9b0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '39bcb368-78d3-41dc-844a-e51f4515a5ea'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '05f44661-ec6f-4c79-bfff-af6e0074645b'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'a2091806-e61d-48ce-be12-0ec5e7bf1254'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '19cc54ac-8faf-4e46-a52b-6f1306017c5b'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'db139ff9-76e7-440f-b655-6f89d28ecb1d'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '87290678-8937-453d-80d6-373cbe568144'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '8435c167-30b8-4f99-8fe5-4fafdbd68c6a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '6e1f4990-9878-419e-964b-98e9b1b4c827'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '8a2e3c2a-c7e8-4daf-a34d-777ad41c8d8f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '523e7da9-24b0-49f3-8a32-b65e0810420a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'b5d9b3bb-99de-4643-bfd3-6b90b0170416'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '5be2c685-63e4-4c81-ab50-9d4bdf99262f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '8455193d-7d88-45e8-a1cd-9a995278ffe9'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '85cc7c7a-359c-4bb0-b473-039b07582fa6'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'a0a6c500-8ea7-4839-97df-86d1a12bc497'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '59f07276-5934-4c15-b3bf-2085497814ee'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'f3aaaa26-813e-4c6c-bd62-d742fa542a74'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '6136f342-ad86-4c72-a2d8-40dd04934773'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'e4acd5ae-b928-4c3b-a41b-33ccf40a4e96'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '2f729372-c008-4dd9-b114-a7a614d1bd18'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'c9a67880-2767-4a2b-87fd-356dbcf60bea'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '7be88525-4117-4ddb-9678-ad9d3817d5d0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'd0cea4cd-f6a7-4f90-830c-e0c5bb3e4841'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '415986c4-5f95-4cef-a4fc-b0ec11401a74'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, 'b0a9e198-cde1-4274-b1c9-910df4f9aec7'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '0cc96b2a-224e-402f-82d0-4c56e684c2e5'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '33a71d82-8e97-4e5e-aca6-d9e94c85d33f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '43be8d95-5cb8-4c07-9763-df18b2a8c124'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('91d2f442-fcd2-4d00-a23c-da207f75242d'::uuid, '3068a06b-cc96-4b6e-b1df-99a028b20cab'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '0c6aa607-83d0-4c31-9903-d9ac1a3434e0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '4ebaed1f-6234-4429-8d2d-89046c8ad1ae'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '8a5eda93-a8ad-454b-add1-60a29495c0ff'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '5d4981d1-c0b2-47c8-8c90-0a4f0e418976'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'f69b6ca6-96c3-4705-9527-6f8a63268331'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'e8647e6b-e68f-40e3-9e25-b370a38bddfe'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '19698d40-230e-4509-a94b-c4673f1269e5'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '339dc084-219f-454e-82dd-7aee895aae2f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '639b9cbe-3654-40d6-ae7f-aaf651c85950'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '9c1a0a40-b398-4c83-a571-1aaeb18e5414'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '65193c54-b3e2-4483-8f4f-b2d89f5f6397'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'a6e7e3de-cb9b-459e-af1f-500a619000a3'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'e83c26d3-8402-4990-881b-a17737eaebe6'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '303e342c-9d05-4f8e-b118-916dbba7f78e'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '448e3dd5-704d-4635-9fd5-5bd5d5d2d6ef'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '826b603b-e40a-4126-8e92-c77e73150d51'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'ed23e77b-f22a-4705-98f8-ad879982b3f3'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'b88a472c-b167-4227-9ef1-1b6f364ecfaa'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '9001d675-3591-4971-a636-11aa64a7a25a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '7cdf9f9b-08ff-42f4-8af6-127c8d802544'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '15b604fb-402f-45ab-b76a-b3506e90a1ce'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'aafd1787-9bfb-4f43-90d8-a988686c400a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '2135a1f8-0d8a-465c-9645-47698454a9b0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '39bcb368-78d3-41dc-844a-e51f4515a5ea'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '05f44661-ec6f-4c79-bfff-af6e0074645b'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'a2091806-e61d-48ce-be12-0ec5e7bf1254'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '19cc54ac-8faf-4e46-a52b-6f1306017c5b'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'db139ff9-76e7-440f-b655-6f89d28ecb1d'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '87290678-8937-453d-80d6-373cbe568144'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '8435c167-30b8-4f99-8fe5-4fafdbd68c6a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '6e1f4990-9878-419e-964b-98e9b1b4c827'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '8a2e3c2a-c7e8-4daf-a34d-777ad41c8d8f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '523e7da9-24b0-49f3-8a32-b65e0810420a'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'b5d9b3bb-99de-4643-bfd3-6b90b0170416'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '5be2c685-63e4-4c81-ab50-9d4bdf99262f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '8455193d-7d88-45e8-a1cd-9a995278ffe9'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '85cc7c7a-359c-4bb0-b473-039b07582fa6'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'a0a6c500-8ea7-4839-97df-86d1a12bc497'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '59f07276-5934-4c15-b3bf-2085497814ee'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'f3aaaa26-813e-4c6c-bd62-d742fa542a74'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '6136f342-ad86-4c72-a2d8-40dd04934773'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'e4acd5ae-b928-4c3b-a41b-33ccf40a4e96'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '2f729372-c008-4dd9-b114-a7a614d1bd18'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'c9a67880-2767-4a2b-87fd-356dbcf60bea'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '7be88525-4117-4ddb-9678-ad9d3817d5d0'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'd0cea4cd-f6a7-4f90-830c-e0c5bb3e4841'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '415986c4-5f95-4cef-a4fc-b0ec11401a74'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, 'b0a9e198-cde1-4274-b1c9-910df4f9aec7'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '0cc96b2a-224e-402f-82d0-4c56e684c2e5'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '33a71d82-8e97-4e5e-aca6-d9e94c85d33f'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '43be8d95-5cb8-4c07-9763-df18b2a8c124'::uuid);
INSERT INTO ums.timetableunit_groups
(group_id, time_table_unit_id)
VALUES('bc2322d6-046b-4b1e-a757-b5cf26eff20a'::uuid, '3068a06b-cc96-4b6e-b1df-99a028b20cab'::uuid);
