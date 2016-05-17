CREATE TYPE DEVELOPER_LEVEL AS ENUM ('JUNIOR','MIDDLE','SENIER','LEAD');

CREATE TABLE developers_team (
	developers_team_id SERIAL PRIMARY KEY,
	developers_team_name VARCHAR(255)NOT NULL
);

CREATE TABLE developer (
	developer_id SERIAL PRIMARY KEY,
	developer_name VARCHAR(255) NOT NULL,
	developer_email VARCHAR(255) NOT NULL UNIQUE,
	developer_pass VARCHAR(255) NOT NULL,
	developers_team_id integer REFERENCES developers_team,
	developer_level VARCHAR(255) NOT NULL,
	is_busy BOOLEAN
);

CREATE TABLE customer (
	customer_id SERIAL PRIMARY KEY,
	customer_name VARCHAR(255) NOT NULL,
	customer_email VARCHAR(255) NOT NULL UNIQUE,
	customer_password VARCHAR(255) NOT NULL
);

CREATE TABLE manager (
	manager_id SERIAL PRIMARY KEY,
	manager_name VARCHAR(255) NOT NULL,
	manager_email VARCHAR(255) NOT NULL UNIQUE,
	manager_passwd VARCHAR(255) NOT NULL,
	developers_team_id integer REFERENCES developers_team
);

CREATE TABLE technical_task (
	technical_task_id SERIAL PRIMARY KEY,
	customer_id integer NOT NULL REFERENCES customer,
	task_name VARCHAR(255) NOT NULL,
	task_description TEXT
);

CREATE TABLE technical_task_job (
	job_id SERIAL PRIMARY KEY,
	technical_task_id integer REFERENCES technical_task,
	job_name VARCHAR(255) NOT NULL,
	developer_level VARCHAR(255) NOT NULL,
	job_description TEXT
);

CREATE TABLE project (
	project_id SERIAL PRIMARY KEY,
	project_name VARCHAR(255),
	project_description TEXT,
	technical_task_id integer REFERENCES technical_task,
	developers_team_id integer REFERENCES developers_team
);

CREATE TABLE project_score (
	project_score_id SERIAL PRIMARY KEY,
	project_id integer REFERENCES project,
	project_score FLOAT NOT NULL
);

CREATE TABLE project_job (
	project_job_id SERIAL PRIMARY KEY,
	project_id integer REFERENCES project,
	developer_id integer REFERENCES developer,
	project_job_name VARCHAR(255),
	project_job_description TEXT NOT NULL,
	developer_level VARCHAR(255) NOT NULL,
	developer_time  interval
);


/* Customer sing in function
 *  IN:
 *    c_email - customer email
 *    c_password - customer password
 *  RETURN
 *     -1 if email not exists
 *     -2 if Incorrect password
 *     customer_id successful identification
 */
CREATE OR REPLACE FUNCTION customer_singin(IN c_email VARCHAR(255), IN c_password VARCHAR(255) ) RETURNS int AS
	$BODY$
  DECLARE
    mail_contains int;
    res int;
  BEGIN
    SELECT customer_id INTO mail_contains FROM customer WHERE customer_email=c_email;
    IF mail_contains IS NULL THEN RETURN -1;
    ELSE
      SELECT customer_id INTO res FROM customer WHERE customer_email=c_email  AND customer_password=c_password;
      IF res IS NULL THEN RETURN -2;
      ELSE RETURN res;
      END IF;
    END IF;
  END
	$BODY$ LANGUAGE plpgsql;

/* Customer sing up function
 *  IN:
 *    c_name - customer name
 *    c_email - customer email
 *    c_password - customer password
 *  RETURN
 *     -1 if email alrady exists
 *     customer_id successful added
 */
CREATE OR REPLACE FUNCTION customer_singup(IN c_name VARCHAR(255),IN c_email VARCHAR(255), IN c_password VARCHAR(255)) RETURNS int AS
	$BODY$
  DECLARE
    mail_contains int;
    c_id int;
  BEGIN
    SELECT customer_id INTO mail_contains FROM customer WHERE customer_email=c_email;
    IF mail_contains IS NOT NULL THEN RETURN -1;
    ELSE
      INSERT INTO customer(customer_name,customer_email,customer_password) VALUES (c_name, c_email,c_password) RETURNING customer_id INTO c_id;
      RETURN c_id;
    END IF;
  END
	$BODY$ LANGUAGE plpgsql;
