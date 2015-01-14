package com.diy.mongodb.wellnessprogram;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Test {

	public static void main(String[] args) {
		try {
			// To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
			// if it's a member of a replica set:
			//MongoClient mongoClient = new MongoClient();
			//MongoClient mongoClient = new MongoClient( "localhost" );
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			// or, to connect to a replica set, with auto-discovery of the primary, supply a seed list of members
			/*
			MongoClient mongoClient = new MongoClient(
				Arrays.asList(
					new ServerAddress("localhost", 27017),
			        new ServerAddress("localhost", 27018),
			        new ServerAddress("localhost", 27019)
				)
			);
			*/
			DB db = mongoClient.getDB( "mydb" );
			DBCollection coll = db.getCollection("testCollection");
			BasicDBObject doc = new BasicDBObject("name", "MongoDB")
	        	.append("type", "database")
	        	.append("count", 1)
	        	.append("info", new BasicDBObject("x", 203).append("y", 102)
	        );
			coll.insert(doc);
			DBObject myDoc = coll.findOne();
			System.out.println(myDoc);
			System.out.println(coll.getCount());
			BasicDBObject query = new BasicDBObject("name", "MongoDB");
			DBCursor cursor = coll.find(query);
			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
			Object o = JSON.parse("{\"field\": \"value\"}");
			DBObject dbObj = (DBObject) o;
			coll.insert(dbObj);
			query = new BasicDBObject("field", "value");
			cursor = coll.find(query);
			try {
			   while(cursor.hasNext()) {
			       System.out.println(cursor.next());
			   }
			} finally {
			   cursor.close();
			}
			mongoClient = null;
			/*
			coll.insert(
				new BasicDBObject(
						"{\"field\": \"value\"}"
				)
			);
			*/
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
