--GROUP HOSTS BY HARDWARE INFO

SELECT 
	cpu_number,
	id as host_id,
	total_mem 
from 
	host_info 
ORDER BY 
	MIN(cpu_number) OVER(PARTITION BY cpu_number ORDER BY total_mem DESC);


--ROUNDS CURRENT TIMESTAMP EVERY 5 MINUTES
CREATE FUNCTION round(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

--AVERAGE MEMORY USAGE
SELECT
	 u.host_id,
	 i.hostname,
	 u.timestamp,
	 AVG((i.total_mem-u.memory_free)*100/i.total_mem) OVER(PARTITION BY u.host_id,
	 round(u.timestamp)) AS avg_used_mem_percentage,round(u.timestamp) 
FROM
	 host_usage u
JOIN 
	host_info i ON u.host_id=i.id;

--DETECT HOST FAILURES
SELECT
    host_id,
    round5(timestamp) AS ts,
    COUNT(*) AS num_data_points
FROM
    host_usage
GROUP BY
    ts,
    host_id
HAVING
        COUNT(*) < 3
ORDER BY
    host_id;


