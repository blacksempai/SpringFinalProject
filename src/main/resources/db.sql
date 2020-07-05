USE `taxes` ;
INSERT account(account_id,login,password_hash,email)
VALUES (1,'root','$2a$10$pDYwHkl7kG4vZKLpOwFhUu9WmhWH6K4VXR0Z55F78TN.iJtT85qZi','root@gmail.com'),
(2,'neo','$2a$10$pDYwHkl7kG4vZKLpOwFhUu9WmhWH6K4VXR0Z55F78TN.iJtT85qZi','Matrix1998@gmail.com'),
(3,'inspector01','$2a$10$.FRdH1A1bRr7bNGlSo8vieVN.KvHw74T6c1N2/BzaYmqc2yDhhbJG','gadget@gmail.com'),
(4,'hanako','$2a$10$w9/5DDQ7IuotUjLEfDw5EuDuspbcjXBiYOOzDgA5vDSPheXdgxBlS','hanako.subaru@gmail.com'),
(5,'inspector02','$2a$10$JSf1zL0Fs3rDJYLShd/0cOYuuoYfln0GiyRFEu8CRLSHtWvAT1Q2S','pink.panter200@mail.ru'),
(6,'inspector03','$2a$10$puy76yUgRD6ecMMAp6xzMO4kVhdNzLmR0kF0kz8ZoqwDCYMh7BEti','johney.english@gmail.com'),
(7,'inspector04','$2a$10$AHhwsrtTusVr4ZxtdvH3xeVUEDnmSr2TldbPWbAqOXdG.cfJ3R3NK','NakedGun@yahoo.com');

INSERT admin(account_id) 
VALUES (1);

INSERT user(account_id,full_name,company_name,passport,address,tax_group) 
VALUES (2,'Томас Андерсон','Навуходоносор','КП454054','75 PARK PLACE 8TH FLOOR NEW YORK NY 10007 United States','FIRST'),
(4,'花子　スバル','花火大会','ИИ324566','東京都港区赤坂２丁目１７−２２','FIRST');

INSERT inspector(account_id,full_name,complaint_number,reports_in_service) 
VALUES (3,'Інспектор Гаджет',0,0),
(5,'Жак Клузо',0,0),
(6,'Джонні Інгліш',0,0),
(7,'Фрэнк Дребин',0,0);
