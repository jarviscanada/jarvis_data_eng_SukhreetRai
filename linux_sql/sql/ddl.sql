CREATE TABLE PUBLIC.host_info(
	id SERIAL NOT NULL PRIMARY KEY,
	hostname VARCHAR(255) NOT NULL UNIQUE, 
	cpu_number INT NOT NULL,
	cpu_architecture VARCHAR(255) NOT NULL, 
	cpu_model VARCHAR(255) NOT NULL, 
	cpu_mhz DOUBLE PRECISION NOT NULL, 
	L2_cache INT NOT NULL, 
	total_mem INT NOT NULL, 
	timestamp TIMESTAMP NOT NULL`);


CREATE TABLE PUBLIC.host_usage(
	"timestamp" TIMESTAMP NOT NULL,
	host_id SERIAL NOT NULL REFERENCES PUBLIC.host_info (id),
	memory_free INT NOT NULL,
	cpu_idle INT NOT NULL,
	cpu_kernel INT NOT NULL,
	disk_io INT NOT NULL,
	disk_available INT NOT NULL);


