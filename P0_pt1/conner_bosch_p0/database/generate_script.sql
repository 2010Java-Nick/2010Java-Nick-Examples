create table player
(
	player_id serial primary key,
	username varchar(50) NOT NULL,
	passphrase varchar(25) NOT NULL,
	current_points integer,
	current_level integer,
	caster_id integer
);

create table caster
(
	caster_id serial primary key,
	caster_name varchar(20) NOT NULL,
	half_caster boolean NOT NULL
);

create table caster_spell
(
	caster_id integer NOT NULL,
	spell_id integer NOT NULL,
	primary key (caster_id, spell_id)
);

create table spell
(
	spell_id serial primary key,
	spell_name varchar(50) NOT NULL,
	spell_level integer NOT NULL
);

ALTER TABLE player ADD CONSTRAINT FK_player_to_caster
	FOREIGN KEY (caster_id) 
		REFERENCES caster (caster_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE caster_spell ADD CONSTRAINT FK_caster_to_spell_list
	FOREIGN KEY (caster_id)
		REFERENCES caster (caster_id) ON DELETE NO ACTION ON UPDATE NO ACTION;
		
ALTER TABLE caster_spell ADD CONSTRAINT FK_spell_to_caster_list
	FOREIGN KEY (spell_id)
		REFERENCES spell (spell_id) ON DELETE CASCADE;