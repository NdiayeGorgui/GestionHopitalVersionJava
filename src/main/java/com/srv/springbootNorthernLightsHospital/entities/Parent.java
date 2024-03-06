/*
 * package com.srv.springbootNorthernLightsHospital.entities;
 * 
 * import java.util.List;
 * 
 * import javax.persistence.Entity; import javax.persistence.GeneratedValue;
 * import javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.ManyToOne; import javax.persistence.OneToMany; import
 * javax.persistence.Table; import lombok.AllArgsConstructor; import
 * lombok.Data; import lombok.NoArgsConstructor; import lombok.ToString;
 * 
 * @Data
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * 
 * @ToString
 * 
 * @Entity
 * 
 * @Table(name = "parents") public class Parent {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; private
 * String nom; private String prenom; private String telephone;
 * 
 * @ManyToOne private Adresse adresse;
 * 
 * @OneToMany(mappedBy = "parent") private List<Admission> admissions; }
 */