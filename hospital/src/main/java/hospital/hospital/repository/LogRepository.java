package hospital.hospital.repository;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import hospital.hospital.enums.LogSeverity;
import hospital.hospital.model.Log;


@Repository
public interface LogRepository extends MongoRepository<Log, Integer> {

	List<Log> findAll();
	
	List<Log> findAllByTimestampBetween(Date from, Date to);

	List<Log> findAllBySeverityAndTimestampBetween(LogSeverity string, Date from, Date to);
	
    int countBySeverityEqualsAndTimestampBetween(LogSeverity severity, Date startDate, Date endDate);
    
    @Query("{$and:[" +
    "{$or:[{$expr: {$eq: [?1, null]}}, {'timestamp':{$lt:?1}}]}, {$or:[{$expr: {$eq: [?0, null]}}, {'timestamp':{$gt:?0}}]}, " +
    "{$or:[{$expr: {$eq: [?2, '']}}, {'ip':{$regex:?2,$options:'s'}}]}, " +
    "{$or:[{$expr: {$eq: [?3, '']}}, {'type':{ $eq: ?3 }}]}, " +
    "{$or:[{$expr: {$eq: [?4, '']}}, {'severity':{ $eq: ?4 }}]}, " +
    "{$or:[{$expr: {$eq: [?5, '']}}, {'message':{$regex:?5,$options:'s'}}]}, " +
    "{$or:[{$expr: {$eq: [?6, '']}}, {'source':{$regex:?6,$options:'s'}}]}, " +
    "{$or:[{$expr: {$eq: [?7, '']}}, {'username':{$regex:?7,$options:'s'}}]}, " +
    "]}")
    Page<Log> searchLogs(Date from, Date to, String ip, String type, String severity, String msg, String source, String username, Pageable pageable);

    /*@Query("{$and:[" +
    "{$or:[{$expr: {$eq: [?7, '']}}, {'username':{$regex:?7,$options:'s'}}]}, " +
    "{$or:[{$expr: {$eq: [?6, '']}}, {'source':{$regex:?6,$options:'s'}}]}, " +
    "{$or:[{$expr: {$eq: [?5, '']}}, {'message':{$regex:?5,$options:'s'}}]}, " +
    "{$or:[{$expr: {$eq: [?4, '']}}, {'severity':{ $eq: ?4 }}]}, " +
    "{$or:[{$expr: {$eq: [?3, '']}}, {'type':{ $eq: ?3 }}]}, " +
    "{$or:[{$expr: {$eq: [?2, '']}}, {'ip':{$regex:?2,$options:'s'}}]}, " +
    "{$or:[{$expr: {$eq: [?0, null]}}, {'timestamp':{$gt:?0}}]}, " +
    "{$or:[{$expr: {$eq: [?1, null]}}, {'timestamp':{$lt:?1}}]}, " +
    "]}")
    List<Log> searchLogsTest(Date from, Date to, String ip, String type, String severity, String msg, String source, String username);
	*/

}