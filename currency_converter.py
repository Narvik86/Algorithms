# Currency Converter
# Taek Kimpip3 install requests

import pip._vendor.requests as requests
import argparse
import sys


def getArguments(args:list):
    parser = argparse.ArgumentParser(description='''Program accepts arguments specifying the starting currency, the
    currency to convert to, and the amount of currency to convert.''')

    parser.add_argument('--from', '-f', required=False, dest='source', type=str, default=False,
                        help='convert from')    

    parser.add_argument('--to', '-t', required=False, dest='to', type=str, default=False,
                        help='convert to')    

    parser.add_argument('--amount', '-a', required=False, dest='amount', type=int, default=False,
                        help='amount of currency of "from"')    
    return parser.parse_args(args)

def main(parserArgs):
    options = getArguments(parserArgs)
    chosenArgs = [
        options.source,
        options.to,
        options.amount
    ]
    url = 'https://api.apilayer.com/fixer/latest?apikey=4E7HR4jM7DC127SQdb0iD0C9KnA09kIE&base=USD&symbols=EUR,GBP'

    if(chosenArgs[0] and chosenArgs[1] and chosenArgs[2]):
        url += 'https://api.apilayer.com/fixer/latest?apikey=4E7HR4jM7DC127SQdb0iD0C9KnA09kIE&base=USD&symbols='+ (chosenArgs[0]) + ',' + (chosenArgs[1])
        r = requests.get(url)
        json_data = r.json()


    else: 
        url = 'https://api.apilayer.com/fixer/latest?apikey=4E7HR4jM7DC127SQdb0iD0C9KnA09kIE&base=USD&symbols=EUR,GBP'
        r = requests.get(url)   
        json_data = r.json()
        print("1 USD is", json_data["rates"]["EUR"], "EUR")


    

if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))