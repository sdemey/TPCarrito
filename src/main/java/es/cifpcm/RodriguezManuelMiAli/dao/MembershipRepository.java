package es.cifpcm.RodriguezManuelMiAli.dao;

import es.cifpcm.RodriguezManuelMiAli.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {
}