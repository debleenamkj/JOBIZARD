package com.stackroute.recruitmentservice.service;

import com.stackroute.recruitmentservice.config.Producer;
import com.stackroute.recruitmentservice.model.JobPosting;
import com.stackroute.recruitmentservice.rabbitmq.domain.JobPostingDTO;
import com.stackroute.recruitmentservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository jobRepository;

    @Autowired
     Producer producer;



    @Override
    public JobPosting  jobPosting(MultipartFile multipartFile,JobPosting jobPosting) throws IOException {
        JobPostingDTO jobPostingDTO = new JobPostingDTO(jobPosting.getCompanyId(), jobPosting.getCompanyName(),jobPosting.getCompanyMail(),jobPosting.getIndustryType(),jobPosting.getLogo(),jobPosting.getJobDetailsList());
        producer.sendMessageToRabbitTemplate(jobPostingDTO);
        byte[] logo = compressBytes(multipartFile.getBytes());
        //multipartFile.getBytes(jobPosting.setLogo(logo));
        //jobPosting.setLogo(multipartFile.getBytes());
        jobPosting.setLogo(logo);
        JobPosting  job = jobRepository.save(jobPosting);
        System.out.println(jobRepository.findById(job.getCompanyId()));
//        return jobRepository.save(jobPosting);
        return  job;
    }



    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @Override
    public Boolean deleteJobPost(String companyId){
        boolean flag = false;
//        JobPosting jobPosting = jobRepository.findById(companyId).get();
//    JobPosting posting = jobRepository.delete(jobPosting );

       if(jobRepository.findById(companyId).isEmpty() ) {
//           jobRepository.deleteById(companyId);
           System.out.println("No such id");
       }else {
           flag = true;
           jobRepository.deleteById(companyId);
       }
       return flag;
    }
    @Override
    public Iterable<JobPosting> showAllJobs(){
        return  jobRepository.findAll();
    }
    @Override
    public List<JobPosting> findBySkills(String skill){
        return jobRepository.findByJobDetailsList(skill);
    }
    @Override
    public Optional<JobPosting> specificJob(String companyId)    {
        return jobRepository.findById(companyId);
    }

}
