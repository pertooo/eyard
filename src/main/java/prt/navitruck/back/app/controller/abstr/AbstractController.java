package prt.navitruck.back.app.controller.abstr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import prt.navitruck.back.app.config.QueryCfg;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;
import prt.navitruck.back.app.model.response.ResponseDTO;




@Slf4j
@MappedSuperclass
public abstract class AbstractController<T extends AbstractEntity, ID extends Serializable> {

    private final AbstractRepository<T, ID> repository;
    private final List<QueryCfg> filterFields = new ArrayList<>();

    public AbstractController(AbstractRepository<T, ID> repository) {
        this.repository = repository;
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity all(){
        return ResponseEntity.ok(ResponseDTO.builder().success(true).content(repository.findAll()).build());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Page<T> list(@PageableDefault(sort = {"id"},  direction = Sort.Direction.DESC) Pageable pageable){

        return repository.findAll(pageable);
    }

    @PutMapping("/put")
    public ResponseEntity save(@RequestBody T obj) {
        T savedObject = repository.save(obj);
        if (savedObject != null) {
            return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity delete(@RequestBody ID id){
        Optional<T> obj = repository.findById(id);
        if (obj.isPresent()) {
            repository.delete(obj.get());
            return ResponseEntity.ok(ResponseDTO.builder().success(true).build());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public long countQuery(){
        return repository.count();
    }

    @GetMapping("/s")
    public Page searchQuery(@RequestParam String q, @PageableDefault Pageable pageable) {
//        if (!this.getFilterFields().isEmpty()) {
//            return null; //repository.findAll(GenericSpecification.query(filterFields,q), pageable);
//        }
        return null;
    }

//    public List<QueryCfg> getFilterFields() {
//        return filterFields;
//    }

}
