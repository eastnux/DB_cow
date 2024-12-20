CREATE TABLE customer2 (
	customer_name varchar(100) NOT NULL,
	customer_id	varchar(100) NOT NULL,
	customer_address varchar(100),
	PRIMARY KEY (customer_id)
);
	

INSERT INTO customer2 values('김길동','김1','대전중구');
INSERT INTO customer2 values('이길동','이2','대전중구선호동');
INSERT INTO customer2 values('한길동','한4','대전중구오류동');
INSERT INTO customer2 values('만길동','만5','대전중구삼성동');
INSERT INTO customer2 values('역길동','역6','대전중구만년동');



SELECT * FROM customer2;

	
CREATE TABLE cow(
	cow_id varchar (20) NOT NULL,
	age INteger NOT NULL,
	health_status varchar(100) NOT NULL,
	gender varchar(10) NOT NULL,
	PRIMARY KEY (cow_id)
);




SELECT * 
 FROM cow;




INSERT INTO cow Values('2',2,'건강','여');

INSERT INTO cow VALUES( '3',3 , '아픔','남');


SELECT * FROM cow;

CREATE TABLE farm(
	farm_id varchar(50) NOT NULL,
	farm_name varchar(50) NOT NULL,
	farm_location varchar(100) NOT NULL,
	PRIMARY key(farm_id)
);
	
INSERT INTO farm values('farm1','우리농장','서울역');
INSERT INTO farm values('farm2','하늘농장','목포역');

SELECT *FROM farm; 
	
CREATE TABLE purchase(
	order_nub INteger NOT NULL,
	order_quantity integer NOT NULL,
	shipping_address varchar(100) NOT NULL,
	PRIMARY KEY (order_nub)
 	);
 
DROP TABLE purchase;

ALTER TABLE purchase
ADD COLUMN order_date varchar(100) NOT NULL;

CREATE TABLE purchase(
    order_nub INTEGER NOT NULL,
    order_date varchar(100) NOT null,
    order_quantity INTEGER NOT NULL,
    shipping_address varchar(100) NOT null,
    PRIMARY KEY (order_nub)
);


INSERT INTO purchase values(1001,'2024-12-20',1,'부산광역시');
INSERT INTO  purchase values(1002,'2024-12-20',2,'대구광역시');

SELECT *
FROM purchase
WHERE order_quantity >= 1;
	
	
	