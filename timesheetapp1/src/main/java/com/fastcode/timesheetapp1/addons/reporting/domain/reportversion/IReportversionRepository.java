package com.fastcode.timesheetapp1.addons.reporting.domain.reportversion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reportversionRepository")
public interface IReportversionRepository
    extends JpaRepository<ReportversionEntity, ReportversionId>, QuerydslPredicateExecutor<ReportversionEntity> {
    @Query("select r from ReportversionEntity r where r.users.id=?1  and r.reportVersion = ?2")
    ReportversionEntity findByReportversionAndUsersId(Long userId, String version);

    @Query("select r from ReportversionEntity r where r.users.id=?1  and r.report.id = ?2 and r.reportVersion = ?3")
    ReportversionEntity findByReportIdAndVersionAndUsersId(Long userId, Long reportId, String version);

    @Query("select r from ReportversionEntity r where r.users.id=?1 ")
    List<ReportversionEntity> findByUsersId(Long userId);
}
