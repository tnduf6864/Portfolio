# 인과추론 시스템
- 특정 키워드가 대통령의 지지율에 영향을 주는지 판단하는 시스템.

인과관계란 상관관계와 다르게 원인과 결과가 명확하게 나타나야 한다.
하지만 상관관계와 인과관계를 구분하기란 쉽지않다.

그래서 수학적으로 인과관계를 판단하는 Granger 인과관계(Causality)를 사용해 
Granger 인과관계가 있다고해서 꼭 인과관계가 존재하는 것은 아니지만
실제로 인과관계가 존재할 경우 Granger 인과관계가 나타나는 특징을 이용해
Granger Causality의 특정 결괏값 기준으로 인과관계가 존재 가능성 유무를 판단하는 시스템을 구현하였다.

키워드 리스트에 있는 키워드를 사용자가 선택하면
데이터 크롤링후 시계열 그래프를 생성해 대통령 지지율과 Granger Causality가 존재하는지 판단 후
어느 시점에 존재하는지 그래프와 함께 표현.


- Granger 인과관계에 대한 설명.
https://intothedata.com/02.scholar_category/timeseries_analysis/granger_causality/

# 이미지

<image src=img/main.png>
<image src=img/wordcloud.png>
<image src=img/keywords_list.png>
<image src=img/select_period.png>
<image src=img/results.png>
