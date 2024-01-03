package com.yanolja_final.domain.notice.repository;

import com.yanolja_final.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
