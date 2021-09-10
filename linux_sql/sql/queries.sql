
--Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)
select 
	cpu_number, 
	id as host_id, 
	total_mem
from host_info
order by cpu_number asc, total_mem desc;

--help function to round current timestamp every 5 mins
create function round5(ts timestamp) returns timestamp as $$
begin
return date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
end;
$$
language PLPGSQL;

--Average used memory in percentage over 5 mins interval for each host.
select 
	host_usage.host_id, 
	host_info.hostname,
	round5(host_usage.timestamp) as time, 
	(avg(total_mem - memory_free) / total_mem *100) :: int as avg_used_mem_percentage  
from host_info
inner join host_usage on host_info.id =host_usage.host_id
group by 
	host_usage.host_id,
	round5(host_usage.timestamp),
	host_info.hostname,
	host_info.total_mem
order by host_usage.host_id asc, round5(host_usage.timestamp) desc;

--Detect host failure when it inserts less than three data points within 5-min interval
select 
	host_id, 
	round5("timestamp") as ts,
	count(*) as num_data_points
from host_usage
group by host_id, round5(timestamp)
having count(*) <3
order by num_data_points asc 
