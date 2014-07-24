USE KeyValueStore;

CREATE TABLE dbo.keymap
(
	item_key varchar(100) NOT NULL,
	item_value varchar(100) NOT NULL
);

INSERT INTO dbo.keymap VALUES ('Lyle','8513850090');
INSERT INTO dbo.keymap VALUES ('Stephen','5664050325');
INSERT INTO dbo.keymap VALUES ('Jin','2986966404');
INSERT INTO dbo.keymap VALUES ('Kennan','3633186698');
INSERT INTO dbo.keymap VALUES ('Stephen','7387711978');
INSERT INTO dbo.keymap VALUES ('Emery','3342575696');
INSERT INTO dbo.keymap VALUES ('Elijah','8010477020');
INSERT INTO dbo.keymap VALUES ('Galvin','1771956300');
INSERT INTO dbo.keymap VALUES ('Amir','3976446850');

SELECT * FROM dbo.keymap;