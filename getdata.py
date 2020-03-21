import tweepy as tw
import pandas as pd
import json

# App Auth
consumer_key = 'XXX'
consumer_secret = 'XXX'
access_key = 'XXX'
access_secret = 'XXX'

# Initialize API
auth = tw.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_key, access_secret)
api = tw.API(auth, wait_on_rate_limit=True)

# Search terms
search_words = ["#coronavirus", "#COVID19", "#CoronavirusOutbreak"]
date_since = "2020-02-01"

# Collect tweets
tweets = tw.Cursor(api.search,
              q=search_words,
              lang="en",
              since=date_since, tweet_mode='extended',
              include_rts=True).items(1000)

tweets_arr = []

# Iterate and print tweets
for tweet in tweets:
    tweets_arr.append(tweet._json)
print("Done")

with open("data.json", "w+") as output:
    output.write(json.dumps(tweets_arr))