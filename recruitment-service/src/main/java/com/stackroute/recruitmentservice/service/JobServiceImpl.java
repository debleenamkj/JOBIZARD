package com.stackroute.recruitmentservice.service;

import com.stackroute.recruitmentservice.config.Producer;
import com.stackroute.recruitmentservice.exception.CompanyNotFound;
import com.stackroute.recruitmentservice.exception.JobsNotFound;
import com.stackroute.recruitmentservice.model.JobDetails;
import com.stackroute.recruitmentservice.model.JobPosting;
import com.stackroute.recruitmentservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private Producer producer;

//    @Autowired
//    JobServiceImpl (Producer producer){
//        producer =this.producer;
//    }

    @Override
    public JobPosting getCompany(String companyName)
    {
        Optional<JobPosting> job1 = jobRepository.findByCompanyName(companyName);
        try {
            if(job1.isEmpty())
            {
                throw new JobsNotFound();
            }
            job1.get().setLogo(decompressBytes(job1.get().getLogo()));
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return job1.get();
    }


    @Override
    public JobPosting addJob(JobPosting jobPosting){
        Optional<JobPosting> job1 = jobRepository.findByCompanyName(jobPosting.getCompanyName());
        try {
            if (job1.isPresent()) {
                System.out.println(job1.get());
                List<JobDetails> jobList = job1.get().getJobDetailsList();
                jobList.addAll(jobPosting.getJobDetailsList());
                // jobList.add(jobPosting.getJobDetailsList());
                job1.get().setJobDetailsList(jobList);
                System.out.println(job1.get().getJobDetailsList());
                jobRepository.save(job1.get());
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return job1.get();
    }

    @Override
    public JobPosting  jobPosting(MultipartFile multipartFile,JobPosting jobPosting) throws IOException {
        System.out.println("in service");
//        JobPostingDTO jobPostingDTO = new JobPostingDTO(jobPosting.getCompanyId(), jobPosting.getCompanyName(),jobPosting.getCompanyEmail(),jobPosting.getIndustryType(),jobPosting.getLogo(),jobPosting.getJobDetailsList());
//        producer.sendMessageToRabbitTemplate(jobPostingDTO);
        Optional<JobPosting> job1 = jobRepository.findByCompanyName(jobPosting.getCompanyName());
        if(job1.isPresent()){
            List<JobDetails> jobList = job1.get().getJobDetailsList();
            jobList.addAll(jobPosting.getJobDetailsList());
            job1.get().setJobDetailsList(jobList);
            jobRepository.save(job1.get());
            return job1.get();
        }
        else {
            byte[] logo = compressBytes(multipartFile.getBytes());
            //multipartFile.getBytes(jobPosting.setLogo(logo));
            //jobPosting.setLogo(multipartFile.getBytes());
            jobPosting.setLogo(logo);
            JobPosting  job = jobRepository.save(jobPosting);
            System.out.println(jobRepository.findById(job.getCompanyId()));
            return  job;
        }
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
        try {
            if(jobRepository.findById(companyId).isEmpty() ) {
//           jobRepository.deleteById(companyId);
                throw new JobsNotFound();
            }else {
                flag = true;
                jobRepository.deleteById(companyId);
            }
        } catch (JobsNotFound e) {
            e.printStackTrace();
        }

        return flag;
    }
    @Override
    public Iterable<JobPosting> showAllJobs() {
        try {
            if(jobRepository.findAll().isEmpty()  ){

                throw new JobsNotFound();
            }else{
                return  jobRepository.findAll();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null ;
    }
    @Override
    public List<JobPosting> findBySkills(String skill){
        return jobRepository.findByJobDetailsList(skill);
    }
    @Override
    public Optional<JobPosting> specificJob(String companyId) {
        try {
            if(jobRepository.findById(companyId).isEmpty()){
                throw new JobsNotFound();
            }else{

                return jobRepository.findById(companyId);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();

    }

}
