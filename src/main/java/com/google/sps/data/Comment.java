package com.google.sps.data;

import java.util.Date;
import java.util.Comparator;

/** 
 * Comment object class
 * This class decribes the Comment object, used in storing information about an individual review
 * that was left by a specific user. It works as an atomic block for keeping track of reviews.
 */
public class Comment {

  private final String author;
  private String messageContent;
  private Date timestamp;
  private Long id;
  private Long negative;
  private Long positive;
  private String username;
  // Does not need place_id since it can inherit that from PlaceReviews

  /**
   * Order Comparator
   * This comparator uses the compare function from the Date class to order Comments.
   */
  public static final Comparator<Comment> ORDER_BY_DATE = new Comparator<Comment>() {
    @Override
    public int compare(Comment a, Comment b) {
      return a.getTime().compareTo(b.getTime());
    }
  };

  /**
   * Order Comparator by Author
   * This comparator orders the comments by their author
   */
  public static final Comparator<Comment> ORDER_BY_AUTHOR = new Comparator<Comment>() {
    @Override
    public int compare(Comment a, Comment b) {
      return a.author.compareTo(b.author);
    }
  };
  
  /** 
   * Constructor
   * Creates a full Comment object, used when an author leaves a review for the first time
   * @param author the message author
   * @param message the content of the message
   * @param timestamp the time of writing
   */
  public Comment(String author, String message, Date timestamp, long id) {
    this.author = author;
    this.messageContent = message;
    this.timestamp = timestamp;
    this.id = id;
  }

  public long getId(){
    return this.id;
  }

  public void setId(long id){
    this.id = id;
  }

  /** 
   * Author Accessor method
   * Accesses private variable
   */
  public String getAuthor() {
    return this.author;
  }

  /** 
   * Message Accessor method
   * Accesses private variable
   */
  public String getMessage() {
    return this.messageContent;
  }

  /** 
   * Time Accessor method
   * Accesses private variable
   */
  public Date getTime() {
    return this.timestamp;
  }

  /** 
   * Update Comment
   * This function is used when a author want's to submit a new comment for a location.
   * This prevents the 'double voting' or 'duplicate feedback' of certain patrons.
   */
  public void updateComment(String message, Date timestamp) {
    this.messageContent = message;
    this.timestamp = timestamp;
  }

  /** 
   * Update Comment
   * This function is used when a author want's to submit a new comment for a location.
   * This prevents the 'double voting' or 'duplicate feedback' of certain patrons.
   */
  public void updateComment(Comment newReview) {
    this.messageContent = newReview.messageContent;
    this.timestamp = newReview.timestamp;
  }
}