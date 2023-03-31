# -*- coding: utf-8 -*-
"""granger_module.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1nkhqElYvbuJfW_Rx1bVpe7u-hgEuiEEg
"""

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from statsmodels.tsa.stattools import adfuller 
from statsmodels.tsa.stattools import kpss  
from statsmodels.tsa.stattools import grangercausalitytests
from datetime import datetime
import datetime
import time
"""
df = pd.read_csv('president.csv', header=None)

df.loc[df[0]=='2017년 10월 1주', 1] = str((67.7+68.5)/2)+'%'
df.loc[df[0]=='2018년 6월 1주', 1] = str((71.4+75.9)/2)+'%'

index = max(df.index)rmfja 

df.loc[index] = ['2022년 3월 5주', '44.80%']
df.loc[index+1] = ['2022년 4월 1주', '43.20%']
df.loc[index+2] = ['2022년 4월 2주', '42.50%']
df.loc[index+3] = ['2022년 4월 3주', '42.10%']
df.loc[index+4] = ['2022년 4월 4주', '42.80%']
df.loc[index+5] = ['2022년 5월 1주', '41.40%']

df = df.dropna(axis=0)

df[1] = df[1].str.replace('%', '')
df[1] = df[1].apply(pd.to_numeric)

df[0] = df[0].str.replace('년 ', '-')
df[0] = df[0].str.replace('월 ', '-')
df[0] = df[0].str.replace('주', 'week')
df[0] = pd.date_range('2017-5-22', '2022-5-9', freq='7D')

df.to_csv('presideint1',index=False)

plt.plot(df[0], df[1])
plt.show()
"""



"""
setPeriodK(키워드csv,start,end) : 키워드 시계열 데이터 기간설정  
setPeriodP(대통령csv,start,end) : 대통령 시계열 데이터 기간설정  
timeSeriesGraph(timeSeriesK,timeSeriesP) : 시계열 그래프 리턴값 없음  
timeSeriesGraphS(timeSeriesK,timeSeriesP) : 정상 시계열 그래프  리턴값 없음  
grangerNS(timeSeriesK,timeSeriesP) :시계열 그레인저 출력  
grangerS(timeSeriesK,timeSeriesP) : 정상 시계열 그레인저 출력  


"""

def setPeriodK(timeSeries,start,end):
    
    df_article = timeSeries.drop_duplicates()

    keyword_count = df_article.groupby('기사작성일').count()
    keyword_count = keyword_count.reset_index()

    week_count_list = []
    a = pd.to_datetime(start) # '2017-5-13/토'
    b = a + datetime.timedelta(days=7) # '2017-5-20/토', 2017/5/19/금에서 +1한거임. for문처럼 range할때 +1해야함

    while True: # while문 안에서 조사기간동안 기사 개수 세는거임
      count = keyword_count[(a < keyword_count['기사작성일']) & (keyword_count['기사작성일'] < b)]['기사제목'].sum()
      week_count_list.append(count)

      if a == pd.to_datetime(end): # 2022-4-30/토요일이 마지막 조사기간에 적용되는 첫 기사 날짜
        break
        
      a = a + datetime.timedelta(days=7)
      b = b + datetime.timedelta(days=7)

    print(len(week_count_list)) #꼭 확인하기 -> 254 이어야함 -> 지지율 더 추가해서 바뀔 수 있음

    start = pd.to_datetime(start)
    end = pd.to_datetime(end)
    start = start + datetime.timedelta(days=6)
    end = end + datetime.timedelta(days=7)
    
    # 2017/5/19/금요일이 첫번째 여론조사 마감일
    # 2022/5/6/금요일이 마지막 여론조사 마감일
    week_article_count = pd.DataFrame(week_count_list, pd.date_range(start, end, freq='7D'))
    week_article_count = week_article_count.reset_index()
    week_article_count.columns = ['date', 'count']

    return week_article_count

def setPeriodP(df,start,end):
  start = pd.to_datetime(start) + datetime.timedelta(days=9)
  end = pd.to_datetime(end) + datetime.timedelta(days=9)
  sub_df = df.loc[df[df[0]==start].index.values[0] : df[df[0]==end].index.values[0]]
  return sub_df

def timeSeriesGraph(timeSeriesK,timeSeriesP):
  fig = plt.figure(figsize=(8,6)) ## 캔버스 생성
  fig.set_facecolor('white')
  ax1 = fig.add_subplot() ## axes 생성
  color1 = 'y'
  ax1.plot(timeSeriesK['date'], timeSeriesK['count'], color=color1)
  color2 = 'b'
  ax2 = ax1.twinx()


  ax2.plot(timeSeriesP[0], timeSeriesP[1], color=color2)
  #ax2.plot(df[0], df[1], color=color2)


  plt.show()

def timeSeriesGraphS(timeSeriesK,timeSeriesP):
  if (adf_test(timeSeriesK['count']) and kpss_test(timeSeriesK['count'])) == False:
    timeSeriesK['diff_count'] = timeSeriesK['count'].diff(1).dropna() #키워드 그레인저
    diff_count = timeSeriesK['count'].diff(1).dropna()  
  else:
    timeSeriesK['diff_count'] = timeSeriesK['count'] #키워드 그레인저
    diff_count = timeSeriesK['count'].copy() 
    del diff_count[0]
  timeSeriesP[2] = timeSeriesP[1].diff(1).dropna()                                         
  diff_df = timeSeriesP[1].diff(1).dropna()                                           

  fig = plt.figure(figsize=(8,6)) ## 캔버스 생성
  fig.set_facecolor('white')
  ax1 = fig.add_subplot() ## axes 생성
  color1 = 'y'
  ax1.plot(timeSeriesK['date'], timeSeriesK['diff_count'], color=color1)
  color2 = 'b'
  ax2 = ax1.twinx()


  ax2.plot(timeSeriesP[0], timeSeriesP[2], color=color2)
  #ax2.plot(df[0], df[1], color=color2)


  plt.show()

#adfuller 테스트
def adf_test(timeseries, pvalue = .05, regression_option = 'ct'):
    dftest = adfuller(timeseries, autolag='AIC', regression = regression_option)
    dfoutput = pd.Series(dftest[0:4], index=['Test Statistic','p-value','Lags Used','Number of Observations Used'])
    for key,value in dftest[4].items():
       dfoutput['Critical Value (%s)'%key] = value


    return dfoutput[1] < pvalue

def kpss_test(timeseries, pvalue = .05, regression_option = 'ct'):
    kpsstest = kpss(timeseries, regression= regression_option)
    kpss_output = pd.Series(kpsstest[0:3], index=['Test Statistic','p-value','Lags Used'])
    for key,value in kpsstest[3].items():
        kpss_output['Critical Value (%s)'%key] = value

    return kpss_output[1] > pvalue

def grangerS(timeSeriesK,timeSeriesP):
  if (adf_test(timeSeriesK['count']) and kpss_test(timeSeriesK['count'])) == False:
    timeSeriesK['diff_count'] = timeSeriesK['count'].diff(1).dropna() #키워드 그레인저
    diff_count = timeSeriesK['count'].diff(1).dropna()  
  else:
    timeSeriesK['diff_count'] = timeSeriesK['count'] #키워드 그레인저
    diff_count = timeSeriesK['count'].copy() 
    del diff_count[0]
  timeSeriesP[2] = timeSeriesP[1].diff(1).dropna()                                         
  diff_df = timeSeriesP[1].diff(1).dropna()         

  granger = pd.DataFrame(diff_count.values, diff_df.values) #sub_df[1]
  #granger = pd.DataFrame(week_article_count['count'].valu|es, df[1].values)

  granger = granger.reset_index()
  granger.columns = ['result', 'cause']     
  b = grangercausalitytests(granger, 12)  
  """
  for i in range(1,13):
    print("Granger Causality")
    print("number of lags (no zero) %d"%i)
    print("ssr based F test:         F=%f  , p=%f  , df_denom=%d, df_num=%d"%(b[1][0]['ssr_ftest'][0],b[1][0]['ssr_ftest'][1],b[1][0]['ssr_ftest'][2],b[1][0]['ssr_ftest'][3]))
    print("likelihood ratio test: chi2=%f , p=%f  , df=%d"%(b[1][0]['ssr_chi2test'][0],b[1][0]['ssr_chi2test'][1],b[1][0]['ssr_chi2test'][2]))
    print("ssr based chi2 test:   chi2=%f  , p=%f  , df=%d"%(b[1][0]['lrtest'][0],b[1][0]['lrtest'][1],b[1][0]['lrtest'][2]))
    print("parameter F test:         F=%f  , p=%f  , df_denom=%d, df_num=%d"%(b[1][0]['params_ftest'][0],b[1][0]['params_ftest'][1],b[1][0]['params_ftest'][2],b[1][0]['params_ftest'][3]))
    print()                          
  """

def grangerNS(timeSeriesK,timeSeriesP):
  granger = pd.DataFrame(timeSeriesK['count'].values, timeSeriesP[1].values) #sub_df[1]
  #granger = pd.DataFrame(week_article_count['count'].values, df[1].values)

  granger = granger.reset_index()
  granger.columns = ['result', 'cause']
  
  b = grangercausalitytests(granger, 12)
"""
  for i in range(1,13):
    print("Granger Causality")
    print("number of lags (no zero) %d"%i)
    print("ssr based F test:         F=%f  , p=%f  , df_denom=%d, df_num=%d"%(b[1][0]['ssr_ftest'][0],b[1][0]['ssr_ftest'][1],b[1][0]['ssr_ftest'][2],b[1][0]['ssr_ftest'][3]))
    print("likelihood ratio test: chi2=%f , p=%f  , df=%d"%(b[1][0]['ssr_chi2test'][0],b[1][0]['ssr_chi2test'][1],b[1][0]['ssr_chi2test'][2]))
    print("ssr based chi2 test:   chi2=%f  , p=%f  , df=%d"%(b[1][0]['lrtest'][0],b[1][0]['lrtest'][1],b[1][0]['lrtest'][2]))
    print("parameter F test:         F=%f  , p=%f  , df_denom=%d, df_num=%d"%(b[1][0]['params_ftest'][0],b[1][0]['params_ftest'][1],b[1][0]['params_ftest'][2],b[1][0]['params_ftest'][3]))
    print()
"""

# 조사기간은 월~금, 발표는 그다음주 월
# 조사기간 3/21~3/25, 발표 3/28
# 기사 코드는 3/19/토 ~ 3/25/금

# 기사 -> 2017/5/13/토 ~ 2017/5/19/금
# 발표 -> 2017/5/22/월

# 5/20~5/26

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from statsmodels.tsa.stattools import grangercausalitytests
from datetime import datetime
import datetime
import time
"""
timeSeries = pd.read_csv('/content/drive/MyDrive/craling/통합 파일/코로나_전체.csv')#, encoding='cp949') # csv파일 업로드 후 사용하려니 자꾸 오류발생해서 드라이브에 연결함 사용하실 때 수정바람
del timeSeries['Unnamed: 0.1']
del timeSeries['Unnamed: 0']
timeSeries['기사작성일'] = pd.to_datetime(timeSeries['기사작성일'])
"""

