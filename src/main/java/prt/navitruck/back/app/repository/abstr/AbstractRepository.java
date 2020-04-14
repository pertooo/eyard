package prt.navitruck.back.app.repository.abstr;


import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> { //

}
