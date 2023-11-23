use geo_POI;

# Sfäriska sträckan, m. jordklotets böjning.
select st_distance_sphere(coordinate, ST_GEOMFROMTEXT('POINT(56.5 16.41)', 4326)) from location;


select name, ST_AsText(coordinate) from location
where st_distance_sphere(coordinate, ST_GEOMFROMTEXT('POINT(56.5 16.41)', 4326)) < 250000;