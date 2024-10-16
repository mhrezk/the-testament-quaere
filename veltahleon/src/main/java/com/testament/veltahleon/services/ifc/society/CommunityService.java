package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Community;

import java.util.Collection;

public interface CommunityService {
    Collection<Community> getCommunitiesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Community> getCommunities();
    Community getCommunityByID(Long id);
    Community getCommunityByName(String name);
    Boolean deleteCommunityByID(Long id);
    Community saveCommunity(Community community);
    Community updateCommunity(Long id, Community community);
    Community modifyCommunity(Long id, Community community);

}
