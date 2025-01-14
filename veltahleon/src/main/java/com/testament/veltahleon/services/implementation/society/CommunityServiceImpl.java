package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.Community;
import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.repositories.society.CommunityRepository;
import com.testament.veltahleon.repositories.society.FamilyRepository;
import com.testament.veltahleon.services.ifc.society.CommunityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Collection<Community> getCommunitiesWithPagination(int pageNumber, int numberOfRecords) {
        return communityRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Community> getCommunities() {
        return communityRepository.findAll();
    }

    @Override
    public Community getCommunityByID(Long id) {
        return communityRepository.findById(id).orElseThrow();
    }

    @Override
    public Community getCommunityByName(String name) {
        return communityRepository.findByName(name);
    }

    @Override
    public Boolean deleteCommunityByID(Long id) {
        List<Family> families = (List<Family>) familyRepository.findByCommunity_Name(communityRepository.findById(id).orElseThrow().getName());
        familyRepository.deleteAllById(families.stream().map(Family::getId).toList());
        communityRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Community saveCommunity(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community updateCommunity(Long id, Community community) {
        Community newCommunity = communityRepository.findById(id).orElseThrow();

        if(community.getName() != null && newCommunity.getName() != community.getName()) {
            newCommunity.setName(community.getName());
        }

        if(community.getCommunitySize() != null && newCommunity.getCommunitySize() != community.getCommunitySize()) {
            newCommunity.setCommunitySize(community.getCommunitySize());
        }

        if(community.getDescription() != null && newCommunity.getDescription() != community.getDescription()) {
            newCommunity.setDescription(community.getDescription());
        }

        return communityRepository.save(newCommunity);
    }

    @Override
    public Community modifyCommunity(Long id, Community community) {
        Community newCommunity = communityRepository.findById(id).orElseThrow();
        newCommunity.setName(community.getName());
        newCommunity.setCommunitySize(community.getCommunitySize());
        newCommunity.setDescription(community.getDescription());
        return communityRepository.save(newCommunity);
    }

    @Override
    public Long countCommunities() {
        return communityRepository.count();
    }

    @Override
    public void updateCommunitySize(Integer size, Long id) {
        communityRepository.updateCommunitySize(size, id);
    }
}
