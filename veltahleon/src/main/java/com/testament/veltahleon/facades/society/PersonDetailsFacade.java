package com.testament.veltahleon.facades.society;

import com.testament.veltahleon.enumerations.JobType;
import com.testament.veltahleon.enumerations.RankType;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.model.entities.politics.Rank;
import com.testament.veltahleon.model.entities.society.Job;
import com.testament.veltahleon.repositories.dogma.ReligionRepository;
import com.testament.veltahleon.repositories.places.NationRepository;
import com.testament.veltahleon.repositories.politics.RankRepository;
import com.testament.veltahleon.repositories.society.JobRepository;
import com.testament.veltahleon.repositories.society.PersonDetailsRepository;
import com.testament.veltahleon.repositories.society.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersonDetailsFacade {

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private ReligionRepository religionRepository;

    @Autowired
    private NationRepository nationRepository;

    @Autowired
    private JobRepository jobRepository;

    public Job createJob() {
        Job job = new Job();
        job.setName("None");
        if(jobRepository.countByName(job.getName()) <= 0) {
            job.setDescription(null);
            job.setJobType(JobType.NONE);
            return jobRepository.save(job);
        } else {
            return jobRepository.findByName(job.getName());
        }
    }

    public Religion createReligion() {
        Religion religion = new Religion();
        religion.setName("None");
        if(religionRepository.countByName(religion.getName()) <= 0) {
            religion.setDescription(null);
            return religionRepository.save(religion);
        } else {
            return religionRepository.findByName(religion.getName());
        }
    }

    public Nation createNation() {
        Nation nation = new Nation();
        nation.setName("None");
        if(nationRepository.countByName(nation.getName()) <= 0) {
            nation.setDescription(null);
            return nationRepository.save(nation);
        } else {
            return nationRepository.findByName(nation.getName());
        }
    }

    public Rank createRank() {
        Rank rank = new Rank();
        rank.setName("None");
        if(rankRepository.countByName(rank.getName()) <= 0) {
            rank.setRankAbove("None");
            rank.setRankBelow("None");
            rank.setRankType(RankType.NONE);
            return rankRepository.save(rank);
        } else {
            return rankRepository.findByName(rank.getName());
        }
    }
}
