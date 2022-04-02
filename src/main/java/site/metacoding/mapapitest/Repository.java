package site.metacoding.mapapitest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Item, Integer> {

}