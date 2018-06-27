-- world

INSERT  INTO public.world (id, columns, name, rows)
    VALUES
      (
        (select nextval('world_seq')),
        24,
        'World',
        14
      );


-- world tiles

/*
CREATE FUNCTION insert_world_tiles(world_id integer)
  RETURNS void
LANGUAGE sql
AS $func$;
DECLARE rowsNumber INTEGER;
  DECLARE colsNumber INTEGER;
  DECLARE rowsCounter INTEGER;
  DECLARE colsCounter INTEGER;
BEGIN
  SET rowsCounter = 0;
  SET colsCounter = 0;
  SELECT public.world.rows INTO rowsNumber FROM world WHERE id = world_id;
  SELECT public.world.columns INTO colsNumber FROm world Where id = world_id;
  WHILE rowsCounter < rowsNumber LOOP
    WHILE colsCounter < colsNumber LOOP
      INSERT INTO public.world_tile (id, column_number, row_number, city_id, world_structure_id, terrain_type_id, world_id)
      VALUES
        (
          (select nextval('worldtile_seq')),
          colsCounter,
          rowsCounter,
          null,
          null,
          (SELECT id FROM public.terrain_type WHERE name = 'grass'),
          world_id
        );
      colsCounter := colsCounter + 1;
    END LOOP;
    rowsCounter := rowsCounter + 1;
  END LOOP;
END;
$func$;

SELECT insert_world_tiles((select id from public.world where name = 'World'));
*/


--resources

INSERT INTO public.resource (id, name, loanable, plunderable, starting_volume, pool_resource)
    VALUES ((select nextval('resource_seq')),'wood', false, true, 200, false);

INSERT INTO public.resource (id, name, loanable, plunderable, starting_volume, pool_resource)
  VALUES ((select nextval('resource_seq')),'stone', false, true, 200, false);

INSERT INTO public.resource (id, name, loanable, plunderable, starting_volume, pool_resource)
  VALUES ((select nextval('resource_seq')),'gold', true, true, 200, false);

INSERT INTO public.resource (id, name, loanable, plunderable, starting_volume, pool_resource)
  VALUES ((select nextval('resource_seq')),'food', false, true, 200, false);

INSERT INTO public.resource (id, name, loanable, plunderable, starting_volume, pool_resource)
  VALUES ((select nextval('resource_seq')),'capacity', false, false, 0, true);

INSERT INTO public.resource (id, name, loanable, plunderable, starting_volume, pool_resource)
  VALUES ((select nextval('resource_seq')),'happiness', false, false, 50, false);

--units

INSERT  INTO public.unit (id, name, display_name, strength, health, speed, capacity)
    VALUES ((select nextval('unit_seq')),'archer', 'Archer', 13, 30, 8, 10);

INSERT  INTO public.unit (id, name, display_name, strength, health, speed, capacity)
  VALUES ((select nextval('unit_seq')),'pikeman', 'Pikeman', 8, 35, 5, 40);

INSERT  INTO public.unit (id, name, display_name, strength, health, speed, capacity)
  VALUES ((select nextval('unit_seq')),'cavalry', 'Cavalry', 25, 55, 15, 20);

INSERT  INTO public.unit (id, name, display_name, strength, health, speed, capacity)
  VALUES ((select nextval('unit_seq')),'settler', 'Settler', 1, 15, 1, 0);

-- building types

INSERT  INTO public.building_type (id, name, display_name, destructible, instances_limit, main_building)
    VALUES ((select nextval('buildingtype_seq')),'castle', 'Castle', false, 1, true);

INSERT  INTO public.building_type (id, name, display_name, destructible, instances_limit, main_building)
  VALUES ((select nextval('buildingtype_seq')),'house', 'House', true, 8, false);

INSERT  INTO public.building_type (id, name, display_name, destructible, instances_limit, main_building)
  VALUES ((select nextval('buildingtype_seq')),'archery', 'Archery Range', true, 1, false);

INSERT  INTO public.building_type (id, name, display_name, destructible, instances_limit, main_building)
  VALUES ((select nextval('buildingtype_seq')),'barracks', 'Barracks', true, 1, false);

INSERT  INTO public.building_type (id, name, display_name, destructible, instances_limit, main_building)
  VALUES ((select nextval('buildingtype_seq')),'stables', 'Stables', true, 1, false);

INSERT  INTO public.building_type (id, name, display_name, destructible, instances_limit, main_building)
VALUES ((select nextval('buildingtype_seq')),'farm', 'Farm', true, 6, false);

INSERT  INTO public.building_type (id, name, display_name, destructible, instances_limit, main_building)
VALUES ((select nextval('buildingtype_seq')),'market', 'Market', true, 3, false);

-- terrains

INSERT INTO public.terrain_type (id, name)
    VALUES ((select nextval('terraintype_seq')),'grass');

-- world structure types

INSERT INTO public.world_structure_type (id, name, display_name)
  VALUES ((select nextval('structuretype_seq')),'city', 'City');

INSERT INTO public.world_structure_type (id, name, display_name)
  VALUES ((select nextval('structuretype_seq')),'tree1', 'Tree');

INSERT INTO public.world_structure_type (id, name, display_name)
  VALUES ((select nextval('structuretype_seq')),'tree2', 'Tree');

INSERT INTO public.world_structure_type (id, name, display_name)
  VALUES ((select nextval('structuretype_seq')),'tree3', 'Tree');

-- unit requirements: Archer

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
(
  (select nextval('requirement_seq')),
  (select id from public.unit where name = 'archer'),
  (select id from public.resource where name = 'gold'),
  10,
  0.0
);

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'archer'),
    (select id from public.resource where name = 'capacity'),
    1,
    1.0
  );

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'archer'),
    (select id from public.resource where name = 'wood'),
    5,
    0.0
  );

-- unit requirements: Pikeman

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'pikeman'),
    (select id from public.resource where name = 'gold'),
    5,
    1.0
  );

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'pikeman'),
    (select id from public.resource where name = 'capacity'),
    1,
    1.0
  );

-- unit requirements: Cavalry

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'cavalry'),
    (select id from public.resource where name = 'gold'),
    20,
    0.0
  );

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'cavalry'),
    (select id from public.resource where name = 'capacity'),
    3,
    1.0
  );

-- unit requirements: Settler

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'settler'),
    (select id from public.resource where name = 'gold'),
    80,
    0.0
  );

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'settler'),
    (select id from public.resource where name = 'food'),
    100,
    0.0
  );

INSERT INTO public.requirement (id, unit_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.unit where name = 'settler'),
    (select id from public.resource where name = 'capacity'),
    15,
    1.0
  );

-- unit production: Archer

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'archer'),
    (select id from public.resource where name = 'gold'),
    -2
  );

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'archer'),
    (select id from public.resource where name = 'food'),
    -4
  );

-- unit production: Pikeman

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'pikeman'),
    (select id from public.resource where name = 'gold'),
    -1
  );

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'pikeman'),
    (select id from public.resource where name = 'food'),
    -4
  );

--unit production: Cavalry

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'cavalry'),
    (select id from public.resource where name = 'gold'),
    -4
  );

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'cavalry'),
    (select id from public.resource where name = 'food'),
    -12
  );

-- unit production: Settler

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'settler'),
    (select id from public.resource where name = 'gold'),
    -20
  );

INSERT INTO  public.production(id, unit_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.unit where name = 'settler'),
    (select id from public.resource where name = 'food'),
    -50
  );

-- building type requirements: Castle

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
    VALUES
    (
      (select nextval('requirement_seq')),
      (select id from public.building_type where name = 'castle'),
      (select id from public.resource where name = 'capacity'),
      -20,
      1.0
    );

--building type requirements: House

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'house'),
    (select id from public.resource where name = 'capacity'),
    -5,
    1.0
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'house'),
    (select id from public.resource where name = 'wood'),
    20,
    0.5
  );

-- building type requirements: Archery Range

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'archery'),
    (select id from public.resource where name = 'wood'),
    80,
    0.5
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'archery'),
    (select id from public.resource where name = 'gold'),
    20,
    0.0
  );

--building type requirements: Barracks

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'barracks'),
    (select id from public.resource where name = 'wood'),
    30,
    0.5
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'barracks'),
    (select id from public.resource where name = 'stone'),
    30,
    0.5
  );

--building type requirements: Stables

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'stables'),
    (select id from public.resource where name = 'wood'),
    60,
    0.5
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'stables'),
    (select id from public.resource where name = 'stone'),
    20,
    0.5
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'stables'),
    (select id from public.resource where name = 'gold'),
    40,
    0.0
  );

-- building type requirements: Farm

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'farm'),
    (select id from public.resource where name = 'wood'),
    30,
    0.5
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'farm'),
    (select id from public.resource where name = 'stone'),
    10,
    0.5
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'farm'),
    (select id from public.resource where name = 'gold'),
    10,
    0.0
  );

-- building type requirements: Market

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'market'),
    (select id from public.resource where name = 'wood'),
    50,
    0.5
  );

INSERT  INTO  public.requirement(id, building_type_id, resource_id, quantity, recovery_coef)
VALUES
  (
    (select nextval('requirement_seq')),
    (select id from public.building_type where name = 'market'),
    (select id from public.resource where name = 'stone'),
    30,
    0.5
  );

--building type productions: Castle

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
    VALUES
      (
        (select nextval('production_seq')),
        (select id from public.building_type where name = 'castle'),
        (select id from public.resource where name = 'gold'),
        10
      );

-- building type production: House

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'house'),
    (select id from public.resource where name = 'gold'),
    -1
  );

--building type productions: Archery Range

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'archery'),
    (select id from public.resource where name = 'gold'),
    -2
  );

--building type productions: Barracks

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'barracks'),
    (select id from public.resource where name = 'gold'),
    -2
  );

-- building type production: Stables

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'stables'),
    (select id from public.resource where name = 'gold'),
    -4
  );

-- building type production: Farm

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'farm'),
    (select id from public.resource where name = 'gold'),
    -1
  );

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'farm'),
    (select id from public.resource where name = 'food'),
    60
  );

-- building type production: Market

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'market'),
    (select id from public.resource where name = 'gold'),
    30
  );

INSERT  INTO public.production(id, building_type_id, resource_id, quantity)
VALUES
  (
    (select nextval('production_seq')),
    (select id from public.building_type where name = 'market'),
    (select id from public.resource where name = 'food'),
    -10
  );
