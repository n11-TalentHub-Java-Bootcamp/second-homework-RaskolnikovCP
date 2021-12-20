package com.ozansaribal.n11_bootcamp_week2_trial.Controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.CategoryConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.MemberConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Converter.ProductConverter;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.CategoryDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.MemberDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDetailsDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Dto.ProductDto;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Category;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Member;
import com.ozansaribal.n11_bootcamp_week2_trial.Entity.Product;
import com.ozansaribal.n11_bootcamp_week2_trial.Exception.MemberNotFoundException;
import com.ozansaribal.n11_bootcamp_week2_trial.Exception.NicknameAndTelephoneByMemberNotFoundException;
import com.ozansaribal.n11_bootcamp_week2_trial.Exception.ProductNotFoundException;
import com.ozansaribal.n11_bootcamp_week2_trial.Service.EntityService.MemberEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberEntityService memberEntityService;

    @GetMapping("")
    public List<MemberDto> findAllMemberList(){

        List<Member> memberList = memberEntityService.findAll();

        List<MemberDto> memberDtoList = MemberConverter.INSTANCE.convertAllMemberListToMemberDtoList(memberList);

        return memberDtoList;
/*
        String filterName = "MemberFilter";

        SimpleFilterProvider filters = getMemberFilterProvider(filterName);

        MappingJacksonValue mapping = new MappingJacksonValue(memberList);

        mapping.setFilters(filters);

        return mapping;*/
    }

    @GetMapping("/{nickname}")
    public List<MemberDto> findMemberByNickname(String nickname){

        List<Member> memberList = memberEntityService.findByNickname(nickname);

        List<MemberDto> memberDtoList = MemberConverter.INSTANCE.convertAllMemberListToMemberDtoList(memberList);

        return memberDtoList;

        // Down below, I've tried to make the connection using
        // "WebMvcLinkBuilder", "SimpleFilterProvider", "MappingJacksonValue" and "EntityModel".
        // Then, I've changed to dao-entityService-controller mechanism.
        // I've tried to implement both systems.
        // If you encounter with any problem, you can check the other system.
        // But if we want to use the system below,
        // we need to change the return value of EntityService methods as Member instead of List<Member>.

/*
        Member member = memberEntityService.findByNickname(nickname);

        if (member == null){
            throw new MemberNotFoundException("Member not found. nickname: " + nickname);
        }

        WebMvcLinkBuilder linkToProduct = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllMemberList()
        );

        MemberDto memberDto = MemberConverter.INSTANCE.convertMemberToMemberDto(member);

        String filterNameByNickname = "MemberDtoFilterByNickname";

        SimpleFilterProvider filters = getMemberFilterProviderByNickname(filterNameByNickname);

        EntityModel entityModel = EntityModel.of(memberDto);

        entityModel.add(linkToProduct.withRel("all-members-with-nickname"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        mapping.setFilters(filters);

        return mapping;
*/
    }

    @GetMapping("/{telephone}")
    public List<MemberDto> findByTelephone(String telephone){

        List<Member> memberList = memberEntityService.findByTelephone(telephone);

        List<MemberDto> memberDtoList = MemberConverter.INSTANCE.convertAllMemberListToMemberDtoList(memberList);

        return memberDtoList;

        // Down below, I've tried to make the connection using
        // "WebMvcLinkBuilder", "SimpleFilterProvider", "MappingJacksonValue" and "EntityModel".
        // Then, I've changed to dao-entityService-controller mechanism.
        // I've tried to implement both systems.
        // If you encounter with any problem, you can check the other system.
        // But if we want to use the system below,
        // we need to change the return value of EntityService methods as Member instead of List<Member>.

        /*
        Member member = memberEntityService.findByTelephone(telephone);

        if (member == null){
            throw new MemberNotFoundException("Member not found. telephone: " + telephone);
        }

        WebMvcLinkBuilder linkToProduct = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllMemberList()
        );

        MemberDto memberDto = MemberConverter.INSTANCE.convertMemberToMemberDto(member);

        String filterNameByTelephone = "MemberDtoFilterByTelephone";

        SimpleFilterProvider filters = getMemberFilterProviderByTelephone(filterNameByTelephone);

        EntityModel entityModel = EntityModel.of(memberDto);

        entityModel.add(linkToProduct.withRel("all-members-with-telephone"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        mapping.setFilters(filters);

        return mapping;
        */
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody MemberDto memberDto){

        Member member = MemberConverter.INSTANCE.convertMemberDtoToMember(memberDto);

        member = memberEntityService.save(member);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(member.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("/{nickname}/{telephone}")
    public ResponseEntity<Object> deleteMemberByNicknameAndTelephone(@RequestParam String nickname, @RequestParam String telephone){

        List<Member> memberNicknameList = memberEntityService.findByNickname(nickname);

        List<Member> memberTelephoneList = memberEntityService.findByTelephone(telephone);

        for (Member member1:memberNicknameList){
            for (Member member2:memberTelephoneList){
                if(member1.getId() != member2.getId()){
                    throw new NicknameAndTelephoneByMemberNotFoundException("" + nickname + " and " +
                                                                            telephone + " info are not matched");
                }
            }
        }

        memberEntityService.deleteByNicknameAndTelephone(nickname, telephone);

        return ResponseEntity.ok("Row with " + nickname + "and" + telephone + " were deleted successfully");

    }

    @PutMapping("")
    public MemberDto update(@RequestBody MemberDto memberDto){

        Member member = MemberConverter.INSTANCE.convertMemberDtoToMember(memberDto);

        member = memberEntityService.save(member);

        MemberDto memberDtoResult = MemberConverter.INSTANCE.convertMemberToMemberDto(member);

        return memberDtoResult;
    }

    private SimpleFilterProvider getMemberFilterProvider(String filterName) {
        SimpleBeanPropertyFilter filter = getMemberFilter();

        return new SimpleFilterProvider().addFilter(filterName, filter);
    }

    private SimpleFilterProvider getMemberFilterProviderByNickname(String filterNameByNickname) {
        SimpleBeanPropertyFilter filter = getMemberFilterByNickname();

        return new SimpleFilterProvider().addFilter(filterNameByNickname, filter);
    }

    private SimpleFilterProvider getMemberFilterProviderByTelephone(String filterNameByTelephone) {
        SimpleBeanPropertyFilter filter = getMemberFilterByTelephone();

        return new SimpleFilterProvider().addFilter(filterNameByTelephone, filter);
    }

    private SimpleBeanPropertyFilter getMemberFilter() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "first_name", "last_name",
                                                            "email", "phone_number", "nickname");
    }

    private SimpleBeanPropertyFilter getMemberFilterByNickname() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "first_name", "last_name", "nickname");
    }

    private SimpleBeanPropertyFilter getMemberFilterByTelephone() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "first_name", "last_name", "phone_number");
    }

}
