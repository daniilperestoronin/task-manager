CREATE TYPE DEVELOPER_LEVEL AS ENUM ('JUNIOR','MIDDLE','SENIER','LEAD');

CREATE TABLE developers_team (
	developers_team_id   SERIAL PRIMARY KEY,
	developers_team_name VARCHAR(255) NOT NULL,
	developer_id         INTEGER REFERENCES developer
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

/* Developer sing in function
 *  IN:
 *    d_email - developer email
 *    d_password - developer password
 *  RETURN
 *     -1 if email not exists
 *     -2 if Incorrect password
 *     developer_id successful identification
 */
CREATE OR REPLACE FUNCTION developer_singin(IN d_email VARCHAR(255), IN d_password VARCHAR(255))
	RETURNS INT AS
$BODY$
DECLARE
	mail_contains INT;
	res           INT;
BEGIN
	SELECT developer_id
	INTO mail_contains
	FROM developer
	WHERE developer_email = d_email;
	IF mail_contains IS NULL
	THEN RETURN -1;
	ELSE
		SELECT developer_id
		INTO res
		FROM developer
		WHERE developer_email = d_email AND developer_email = d_password;
		IF res IS NULL
		THEN RETURN -2;
		ELSE RETURN res;
		END IF;
	END IF;
END
$BODY$ LANGUAGE plpgsql;

/* Developer sing up function
 *  IN:
 *    d_name - developer name
 *    d_email - developer email
 *    d_password - developer password
 *    d_level - developer level
 *    d_team - developer team name
 *  RETURN
 *     -1 if email alrady exists
 *     -2 team not exist
 *     developer_id successful added
 */
CREATE OR REPLACE FUNCTION developer_singup(IN d_name     VARCHAR(255), IN d_email VARCHAR(255),
																						IN d_password VARCHAR(255),
																						IN d_level    VARCHAR(255), IN d_team INTEGER)
	RETURNS INT AS
$BODY$
DECLARE
	mail_contains INT;
	team_exist    INT;
	c_id          INT;
BEGIN
	SELECT developer_id
	INTO mail_contains
	FROM developer
	WHERE developer_email = d_email;
	SELECT developers_team_id
	INTO team_exist
	FROM developers_team
	WHERE developers_team_id = d_team;
	IF mail_contains IS NOT NULL
	THEN RETURN -1;
	ELSIF team_exist IS NULL
		THEN RETURN -2;
	ELSE
		INSERT INTO developer (developer_name, developer_email, developer_pass, developers_team_id, developer_level, is_busy)
		VALUES (d_name, d_email, d_password, d_team, d_level, FALSE)
		RETURNING developer_id
			INTO c_id;
		RETURN c_id;
	END IF;
END
$BODY$ LANGUAGE plpgsql;


/* Manager sing in function
 *  IN:
 *    m_email - manager email
 *    m_password - manager password
 *  RETURN
 *     -1 if email not exists
 *     -2 if Incorrect password
 *     mamager_id successful identification
 */
CREATE OR REPLACE FUNCTION manager_singin(IN m_email VARCHAR(255), IN m_password VARCHAR(255))
	RETURNS INT AS
$BODY$
DECLARE
	mail_contains INT;
	res           INT;
BEGIN
	SELECT manager_id
	INTO mail_contains
	FROM manager
	WHERE manager_email = m_email;
	IF mail_contains IS NULL
	THEN RETURN -1;
	ELSE
		SELECT manager_id
		INTO res
		FROM manager
		WHERE manager_email = m_email AND manager_passwd = m_password;
		IF res IS NULL
		THEN RETURN -2;
		ELSE RETURN res;
		END IF;
	END IF;
END
$BODY$ LANGUAGE plpgsql;

/* Manager sing up function
 *  IN:
 *    m_name - customer name
 *    m_email - customer email
 *    m_password - customer password
 *    team_name - team name
 *  RETURN
 *     -1 if email alrady exists
 *     team_id successful added
 */
CREATE OR REPLACE FUNCTION manager_singup(IN m_name     VARCHAR(255), IN m_email VARCHAR(255),
																					IN m_password VARCHAR(255),
																					IN team_name  VARCHAR(255))
	RETURNS INT AS
$BODY$
DECLARE
	mail_contains INT;
	t_id          INT;
	m_id          INT;
BEGIN
	SELECT manager_id
	INTO mail_contains
	FROM manager
	WHERE manager_email = m_email;
	IF mail_contains IS NOT NULL
	THEN RETURN -1;
	ELSE
		INSERT INTO developers_team (developers_team_name) VALUES (team_name)
		RETURNING developers_team_id
			INTO t_id;
		INSERT INTO manager (manager_name, manager_email, manager_passwd, developers_team_id)
		VALUES (m_name, m_email, m_password, t_id)
		RETURNING manager_id
			INTO m_id;
		RETURN m_id;
	END IF;
END
$BODY$ LANGUAGE plpgsql;