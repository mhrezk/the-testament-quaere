package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.Community;
import com.testament.veltahleon.repositories.society.CommunityRepository;
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

        if(community.getDescription() != null && newCommunity.getDescription() != community.getDescription()) {
            newCommunity.setDescription(community.getDescription());
        }

        return communityRepository.save(newCommunity);
    }

    @Override
    public Community modifyCommunity(Long id, Community community) {
        Community newCommunity = communityRepository.findById(id).orElseThrow();
        newCommunity.setName(community.getName());
        newCommunity.setDescription(community.getDescription());
        return communityRepository.save(newCommunity);
    }
}
