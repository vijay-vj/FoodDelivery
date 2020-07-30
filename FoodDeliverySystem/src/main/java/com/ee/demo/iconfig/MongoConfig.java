package com.ee.demo.iconfig;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.transaction.NoTransactionException;

import com.ee.demo.iutils.Ioutils;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;
 
import java.net.URLEncoder;

import javax.annotation.PreDestroy;

@Configuration
@Primary
@PropertySource("classpath:application.properties")
public class MongoConfig extends AbstractMongoConfiguration {
	private Logger logger = Logger.getLogger(MongoConfig.class);
	private Ioutils I$utils = new Ioutils();
	public static MongoClient mongo = null;
	static MongoClientURI uri = null;
	public ClientSession session = null;
	static String DBServer;
	static String DBPort;
	static String DBName; 
	static String DBUser;
	static String DBPass; 
	public static MongoDatabase getMongoDB() {
		return mongo.getDatabase(DBName);
	}

	public static MongoClient getMongo() {
		return mongo;
	}

	@Override
	protected String getDatabaseName() {
		return DBName;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	@Override
	public Mongo mongo() throws Exception {

		if (mongo == null) {
			logger.debug("Starting Mongo");
			System.out.println("Starting Mongo.class.New");
			DBServer = I$utils.$strValNullIf(PropLoader.env.getProperty("db.mongo.host"), "localhost");
			DBPort = I$utils.$strValNullIf(PropLoader.env.getProperty("db.mongo.port"), "27017");
			DBName = I$utils.$strValNullIf(PropLoader.env.getProperty("db.mongo.database"),
					"Impacto" + PropLoader.env.getProperty("MajorVersion"));
			MongoClientOptions.Builder options = MongoClientOptions.builder()
					.connectionsPerHost(I$utils.$intValNullIf(PropLoader.env.getProperty("MaxMongoPoolSize"), 100))
					.minConnectionsPerHost(I$utils.$intValNullIf(PropLoader.env.getProperty("MinMongoPoolSize"), 10))
					.maxConnectionIdleTime(
							I$utils.$intValNullIf(PropLoader.env.getProperty("MongoConnIdleTime"), (360 * 1_000)))
					.maxConnectionLifeTime(
							I$utils.$intValNullIf(PropLoader.env.getProperty("MongoConnLifeTime"), (360 * 1_000)));
			; 
			String DBUName = I$utils.$strValNullIf(PropLoader.env.getProperty("db.mongo.user"), "~NONE~");
			String DBPass = I$utils.$strValNullIf(PropLoader.env.getProperty("db.mongo.pass"), "~NONE~");
			 
			if (I$utils.$iStrFuzzyMatch(DBUName, "~NONE~"))
				uri = new MongoClientURI("mongodb://" + DBServer + ":" + DBPort + "/" + DBName, options);
			else {
				String Pkey = I$utils.$strValNullIf(PropLoader.env.getProperty("core.pkey"),
						"123421lksjdlfji92349902ljfklsdnsdjfe"); 
				DBUName = URLEncoder.encode(DBUName, "UTF-8");
				DBPass = URLEncoder.encode(DBPass, "UTF-8");
				// #PM000003 Ends
				uri = new MongoClientURI(
						"mongodb://" + DBUName + ":" + DBPass + "@" + DBServer + ":" + DBPort + "/" + DBName, options);
			}
			;

			logger.info("About to connect to MongoDB @ ");
			// logger.info("About to connect to MongoDB @ "+uri.toString());
			try {
				mongo = new MongoClient(uri);
				mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
			} catch (MongoException ex) {
				logger.error("An error occoured when connecting to MongoDB", ex);
				ex.printStackTrace();
			} catch (Exception ex) {
				logger.error("An error occoured when connecting to MongoDB", ex);
				ex.printStackTrace();
			}
			// To be able to wait for confirmation after writing on the DB
			mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		}
		return mongo;
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.ee.demo";
	}

	@PreDestroy
	public void MongoClose() {
		if (mongo != null)
			try {
				mongo.close();
				logger.info("Mongo Closed");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public ClientSession createSession() {
		session = mongo.startSession();
		session.startTransaction(TransactionOptions.builder().writeConcern(WriteConcern.MAJORITY).build());
		return session;

	}

	public ClientSession getSession() {
		return session;

	}

	public void commitSession() {
		try {
			session.commitTransaction();
			session.close();
			session= null; 
		} catch (MongoException | NoTransactionException e) {
			e.printStackTrace();
		}
	}

	public void abortSession() {
		try {
			session.abortTransaction();
			session.close();
			session= null; 
		} catch (MongoException | NoTransactionException e) {
			e.printStackTrace();
		}
	}

	public MongoConfig() {
		// Const
	}
} 