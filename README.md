최대 유량(maximum flow) 문제는, 대표적인 조합론적 최적화(combinational optimization) 문제이다. 

이 문제를 이해하기 위해, 일상생활에서의 예시를 들어 보겠다. 

서울역에서, 부산역까지 물류를 기찻길을 통해서 보낸다고 해 보자. 당연히 서울역과 부산역 사이에는 많은 역들이 존재한다.

그리고, 각 역을 잇는 기찻길에는, 한번에 보낼 수 있는 양에 "제한"이 있다고 하자. 예를 들면, **"이 기찻길을 통해서는, 5톤 이상의 화물을 보낼 수 없습니다"** 와 같은 제한들이 주어진다.

이때, 서울역에서 부산역까지, 최대로 보낼 수 있는 물류의 양은 얼마나 될까? 더 나아가서, 기찻길의 이용료까지 지불해야 할때, 최소 비용으로, 최대의 물류를 어떻게 보낼 수 있을까?

이와 같은 문제를 해결하는 분야가 **Network Flow** 이론이다.

### Flow network의 정의

maximum flow 문제를 엄밀하게 정의하기에 앞서서, 일단 Flow network란 무엇인지부터 정의해야 한다. 

directed graph G=(V, E) 가 있다고 하자. 그리고, 우리는, 두 함수 f, c를, 그래프의 간선에 부여한다. 

#### flow f

f : flow f는, 위의 예시로 비유하자면, "기찻길에 지나가는 물류의 양" 이다. 
함수 f는, f : $E\;\rightarrow\;R$ 이며, 음의 값을 가질 수 있다. 음의 유량은, 우리가 보고 있는 그래프가 directed graph라는 점을 감안할 때, 현재 간선 방향의 반대 방향으로 유량이 흐르고 있음을 의미한다. 

예를 들어서, u -> v 간선의 유량 f(u, v) = -3이라면, 이는 v -> u 방향으로 3의 유량이 흐르고 있음을 의미한다. 

#### capacity c

c : capacity c는, 위의 예시로 비유를 하자면, 각 역을 잇는 기찻길에 있는, "물류의 양의 제한" 이다. 

함수 c는, c : $E\;\rightarrow\;R^+$ 이며, 음의 값은 가지지 않는다. (이 기찻길로는 -3톤의 무게가 지나갈 수 있습니다..?)

u -> v 간선의 용량은,  c(u, v) 로 표현한다. 

#### source vertex s

flow network에는, 두 개의 특별한 정점이 존재한다. 소스 s는, "유량이 발생하기만 하는 정점" 이다, 위의 예시로 비유하자면, 서울이 소스가 된다. 

#### sink vertex t

flow network에는, 싱크 t도 존재하는데, 싱크 t는, "유량이 소멸하기만 하는 정점" 이다. 위의 예시로 비유하자면, 부산이 싱크가 된다. 


이렇게, directed graph G=(V, E) source s, sink t $\in$ V , $\forall e \in E$ , $\exists  c(e) : e \rightarrow R^+, \exists  f(e) : e \rightarrow R$
인, G=(V, E)를, flow network 라고 한다. 



### Flow network 가 만족하는 두 가지 성질

#### 1. capcity constraint

$\forall e \in E, f(e) \leq c(e)$

모든 간선에 흐르는 유량은, 간선의 용량을 넘을 수 없다. 생각해 보면 당연한데, 이 기찻길로는 5톤 이상의 물류가 지나가면, 기찻길이 무너집니다. 라고 했는데,(용량 5) 이 기찻길로 6톤의 물류를 보내면(유량 6), 당연히 기찻길이 무너진다. 

#### 2. flow conservation constraint

$flow\;network\;G=(V, E)\;\;\forall v\;\in\;V\;\setminus\; \{s, t\},$

$\sum_{e\;out\;of\;v}\;\;f(e)\;\;\leq\;\;\sum_{e\;into\;v}\;\;f(e)$

소스와 싱크를 제외한, flow network G의 임의의 정점에 대해서, 이 정점으로 들어오는 유량과, 나가는 유량의 합은 동일해야 한다. 이는 생각해보면 당연한데, 예를 들어서, 서울->수원->대전->부산 으로 8톤의 물류를 보내고 있다고 하자. 그런데, 수원으로 8톤의 물류가 들어오고, 7톤이 나간다면, 남은 1톤은 어디 꽁쳐두는거니까, 이런 상황을 방지하고자 flow conservation constraint를 건 것이다.

간단한 flow network의 예시를 살펴보자.

![[스크린샷 2024-04-06 오후 4.31.35.png]]

소스, 싱크는 각각 s, t로 표시되어 있고, 각 간선에는, a/b 형식으로 값이 매겨져 있는데, 

a(빨간색 글씨) 는 flow, b(검은색 글씨)는 capacity이다. 

그렇다면, 이 flow network 상에, 현재 흐르는, flow의 총합은 어떻게 구하는가?  이, "flow의 총합"을 net flow 라고 한다.
### net Flow

flow network에 흐르는 flow의 총합 net flow는, 다음과 같이 구한다. 

$\sum_{e\;out\;of\;s}\;f(e)\;\;-\;\sum_{e\;into\;s}\;f(e)$

flow 는 source에서만 발생하므로, 우리는 s에서 나오는 간선들의 flow의 합에서, s로 들어가는 간선들의 flow 합을 뺌으로써, 실제로 흐르는 유량인 net flow를 구할 수 있다. 

### maximum flow 문제의 정의

flow network G=(V, E) 상에서, source s, sink t 사이에 흐를 수 있는, "최대" 유량을 구하는 문제가 maxflow  problem이다. 이는, optimization problem이다. 

### Flow network에 유량 흘리기

그럼, 이제 flow network와, maximum flow 문제를 정의했다. 그러면, 이렇게 구성된 flow network에 유량을 어떻게 흘릴 수 있을까? 

일단, 아주 간단히 생각해 보자. 일상생활의 예를 빌려 와서, 서울 -> 수원 -> 대전 -> 부산 으로 보내기로 하자. 이 경로는 그냥 아무렇게나 정한 것이다. 그러면, 이제 실제로 물류를 보낼 건데, 뭐가 중요할까? 어렵지 않게, "용량"이 중요하다는 것을 알 수 있다. 

서울 -> 수원 : 6
수원 -> 대전 : 5
대전 -> 부산 : 7

이렇게 용량 제한이 걸려 있다고 하자, 이 경로로는 얼마만큼의 물류를 보낼 수 있을까?

5만큼의 물류를 보낼 수 있다. 이걸 어떻게 알았을까? 생각을 해 보면, 

"우리가 선택한 경로의 간선들 중, 가장 작은 용량의 크기" 만큼 유량을 보낼 수 있다. 

그러면, 여기서 몇 가지 궁금증이 생기는데, 과연 이 경로를 어떻게 선택해야 net flow를 최대화할 수 있는가? 
그럼, 경로상의 가장 작은 용량의 간선을 제외하고는, 추가적인 유량을 보낼 수 없는가? 

첫 번째 궁금증, 두 번째 궁금증 모두, residual capacity(잔여 용량), residual network(잔여 네트워크)를 정의하여 해결할 수 있다. 

### Residual Network(잔여 네트워크) 의 정의

Residual network라는 것이 있다. 이는 무엇일까? 이를 알기 위해서는, Residual Capacity(잔여 용량)을 먼저 정의해야 한다. 

#### Residual Capacity(잔여 용량)

잔여 용량은, flow network의 성질인, capacity constraint를 통해 정의되는 개념이다. flow constraint에 따라서, $f(e)\;\leq\;c(e)$ 임을 알고 있다. 여기서, 우리는 잔여 용량 r(e)를 정의할 수 있다. 

$residual\;capacity\;r(e)\;=\;c(e)\;-\;f(e)$

로 정의하고, capacity constraint를 통해서,

$r(e)\;\ge\;0$

임을 알 수 있다. 

그러면, 이 잔여 용량 r(e) 가 의미하는 바는 무엇일까? 이는, **"추가로 흘릴 수 있는 유량"** 을 나타낸다. 만약, 어떤 간선 e의 잔여 용량 $r(e)\;=\;0$ 이라면, 이 간선 e는, saturated(포화) 되었다고 한다. 이 간선으로는 더 이상 유량이 추가적으로 흐를 수가 없는 것이다. 

그리고, Residual Network를 이해하기 위해서는, flow network의 성질 중, 추가로 알아야 할 것이 하나 있다. 

#### skew-symmetric flow

$flow\;network\;G=(V,\;E),\;\;\forall\;u,\;v\;\in\;V,\;\forall\;(u,\;v)\;\in\;E,\;\;f(u,\;v)\;=\;-f(v,\;u),\;c(v,\;u)\;=\;0$


정리하면, flow network G=(V, E)의 모든 간선 e에 대해서, (u, v) 간선에 3의 유량이 흐르고 있다면, 그 역방향 간선인 (v, u)에는, -3의 유량이 흐르고 있는 것이다. 물론, 이는 실제 Flow network에는 포함되지 않는다.(그래서 역방향 간선의 용량은 0이다.)

그럼, 도데체 이런 기괴한 정의를 왜 하는가? 이는 Residual capacity, Residual Network의 정의와 관련 있다. 

예시를 들어 보자. 간선 (u, v)에 대해서, f(u, v) = 3, c(u, v) = 5 라고 하자. 그러면, 역방향 간선(v, u)는, f(v, u) = -3, c(v, u) = 0의 값을 갖는다. 그러면, 한번 역방향 간선의 잔여 용량을 구해 보자. 

$r(v,\;u)\;=\;c(v,\;u)\;-\;f(v,\;u)\;=\;0\;-\;(-3)\;=\;3$

역방향 간선 (v, u)의 잔여 용량이 3이라는 것은, 역방향 간선을 통해 3의 유량을 흘릴 수 있다는 것인데, 이게 도데체 무슨 말이냐 하면, 우리는 지금 순방향 간선(u, v)로 3의 유량을 흘리고 있고, 그 유량을 흘림과 동시에, 역방향 간선의 잔여 용량이 3이 되었다.  여기서 알 수 있는 것은, 

역방향 간선으로 3의 유량을 흘리는 것은, 우리가 순방향 간선으로 흘리는, 3의 유량을 취소 

하는 것과 동일한 효과를 지닌다. 그래서, skew-symmetric flow의 존재 의의는, 

**우리가 얼마만큼의 유량을 취소** 할 수 있는지를 보여주는 것이다. 

#### Residual network의 정의

이제, Residual network를 정의할 준비가 되었다. 

$given\;flow\;f\;and\;Flow\;Network\;G=(V,\;E),\;Residual\;Network\;G_f\;is$

$G_f=(V,\;E_f),\;and\;E_f\;is\;defined\;as\;$

$E_f\;=\;\{(u,\;v)\;\in\;E\;|\;r(u,\;v)\;>\;0\}\;\;\cup\;\;\{(v,\;u)\;|\;(u,\;v)\;\in\;E\;\;and\;\;r(v,\;u)\;>\;0\}$

이렇게 정의된다. Residual network $G_f$ 의 간선 집합 $E_f$ 는, 정방향 간선 중 양의 잔여 용량을 가지고 있거나, 역방향 간선 중, 양의 잔여 용량을 가지고 있는 간선들의 집합이다. 

residual network의 간선의 용량이 양수라는 점이 중요하다. 

이렇게 구성된 Residual Network는, 유량을 더 흘릴 수 있는 간선 (u, v) 와, 현재 흐르는 유량을 취소할 수 있는 간선 (v, u)를 포함한다.

#### Residual Network의 용량의 의미

Residual Network에서의 용량은 무엇을 의미하는가?  잔여 네트워크의 용량은, $c_f$ 로 표기한다. 

-> 해당 간선에서, 더 흘릴 수 있는 유량을 의미한다. 

잔여 네트워크는, 현재 흘린 유량에서, 더욱 유량을 흘리기 위해서 만들어낸 것이다. 

### Augmenting Path(증가 경로)

augmenting path에 대해서 알아보자. 우리가 Residual network를 만들어낸 이유는, 현재 흘리는 유량을 증가시키기 위해서이다. 

Residual network에서도 s, t가 있다. 그럼, 우리는 Residual network $G_f$ 에서, s, t를 잇는 임의의 단순 경로 P를 하나 생각하자. 단순 경로는 사이클이 없는 경로를 의미한다. 그리고, 그 경로상의 유량 $f_P$ 를 다음과 같이 정의하자. 

$f_P\;=\;\begin{cases}min\{c_f(e)\;:\;e\;\in\;P\}, & if\;e\;\in\;P \newline0 & \text{Otherwise}\end{cases}$

아까 그냥 Flow Network에서, 유량을 흘리는 것과 동일하다. 만약, $s\;\rightarrow\;t$ 로의 단순 경로 P를 찾을 수 있다면, 그 경 P 상의 간선의 용량 $c_f(e),\;e\;\in\;P$ 중, 최소만큼의 유량을 흘릴 수 있을 것이다.

그러면, 기존에 주어진 $flow\;f$ 와, 단순 경로 상의 $flow\;f_P$ 를 합치는 것은 어렵지 않을 것이다.  이를, 다음과 같이 표기한다. 

$f\;\uparrow\;f_P(u,\;v)$ 그리고, 이는 다음과 같이 표기할 수 있다. 

$f\;\uparrow\;f_P(u,\;v)\;=\;\begin{cases}f(u,\;v)\;+\;f_P(u,\;v), & if\;(u,\;v)\;\in\;P \newline f(u,\;v)\;-\;f_P(v,\;u) & if\;(v,\;u)\;\in\;P \newline f(u,\;v) & \text{Otherwise}\end{cases}$

간단하다. 첫 번째 경우는, 정방향 간선을 통해서, 현재 유량을 증가시키는 것을 나타내고, 두 번째 경우는, 현재 유량을 $f_P(v,\;u)$ 만큼 취소하는 것을 나타낸다. 

이러한 경로 $P$ 를, augmenting path(증가 경로) 라고 한다. 중요한 점은, augementing path P는, "소스와 싱크를 잇는 단순 경로" 라는 사실이다. 

그러면 이렇게, 잔여 네트워크, 증가 경로를 이용해서, 현재 $flow\;f$ 를 증가시킬 수 있다는 것을 알아보았다. 

그러면, 이를 반복적으로 적용하면, maximal 한 flow를 구할 수 있을 것이다. 그러니까, 우리가 찾고자 하는 "최대 유량" 은 다음과 같이 구할 수 있을 것이다

### Maximum Flow 구하기

$flow\;network\;G=(V,\;E)\;$ 가 주어졌을 때, 우리는, $residual\;network\;G_f\;$ 를 구성하고, 이 $G_f$ 상에서, $augmenting\;path\;P$ 를 찾아서, 현재 $flow\;f$ 를 증가시킬 수 있다. 증가 시킨 후, 다시 $residual\;network$ 를 구성함으로써, 반복적으로 유량을 증가시키다가, 더 이상 $augmenting\;path\;P$ 를 찾을 수 없다면,(소스-싱크를 잇는 경로가 하나도 없다. 곧, $residual\;network$ 를 구성했을때, 싱크까지 도달하지 못하고 중간에 끊어진다.)
현재 $flow\;f$ 가, 곧 $flow\;netwotk\;G$ 의 $maximum\;flow$ 가 된다. 


link=[[Maximum Flow, Minimum Cut]]
link=[[Ford-Fukerson method]]
