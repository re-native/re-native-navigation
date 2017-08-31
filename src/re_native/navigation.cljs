(ns re-native.navigation
  (:require [reagent.core :as r]
            [re-frame.core :as re]))

(def react-navigation (js/require "react-navigation"))

; Core
(def createNavigationContainer (.-createNavigationContainer react-navigation))
(def StateUtils                (.-StateUtils react-navigation))
(def addNavigationHelpers      (.-addNavigationHelpers react-navigation))
(def NavigationActions         (.-NavigationActions react-navigation))

; Navigators
(def createNavigator (.-createNavigator react-navigation))
(def StackNavigator  (.-StackNavigator react-navigation))
(def TabNavigator    (.-TabNavigator react-navigation))
(def DrawerNavigator (.-DrawerNavigator react-navigation))

; Routers
(def StackRouter (.-StackRouter react-navigation))
(def TabRouter   (.-TabRouter react-navigation))

; Views
(def Transitioner (.-Transitioner react-navigation))
(def CardStack    (.-CardStack react-navigation))
(def DrawerView   (.-DrawerView react-navigation))
(def TabView      (.-TabView react-navigation))

(def NavigationActionsBACK (aget NavigationActions "BACK"))
(def NavigationActionsINIT (aget NavigationActions "INIT"))
(def NavigationActionsNAVIGATE (aget NavigationActions "NAVIGATE"))
(def NavigationActionsRESET (aget NavigationActions "RESET"))
(def NavigationActionsSET_PARAMS (aget NavigationActions "SET_PARAMS"))
(def NavigationActionsURI (aget NavigationActions "URI"))
(def back (aget NavigationActions "back"))
(def init (aget NavigationActions "init"))
(def navigate (aget NavigationActions "navigate"))
(def reset  (aget NavigationActions "reset"))
(def setParams (aget NavigationActions "setParams"))
(def uri (aget NavigationActions "uri"))

(defn getStateForAction [router action]
  (.getStateForAction router action))

(defn getActionForPathAndParams [router path params]
  (.getActionForPathAndParams router path params))

(defn getPathAndParamsForState [router state]
  (.getPathAndParamsForState router state))

(defn getComponentForState [router state]
  (.getComponentForState router state))

(defn getComponentForRouteName [router route-name]
  (.getComponentForRouteName router route-name))

(defn getRouter [navigator]
  (.-router navigator))

(assert react-navigation)
(assert createNavigationContainer)
(assert StateUtils)
(assert addNavigationHelpers)
(assert NavigationActions)
(assert createNavigator)
(assert StackNavigator)
(assert TabNavigator)
(assert DrawerNavigator)
(assert StackRouter)
(assert TabRouter)
(assert Transitioner)
(assert CardStack)
(assert DrawerView)
(assert TabView)
(assert NavigationActionsBACK)
(assert NavigationActionsINIT)
(assert NavigationActionsNAVIGATE)
(assert NavigationActionsRESET)
(assert NavigationActionsSET_PARAMS)
(assert NavigationActionsURI)
(assert back)
(assert init)
(assert navigate)
(assert reset)
(assert setParams)
(assert uri)

  ;; navigator
(defn make-stack-navigator [route-configs navigator-config] (r/adapt-react-class (StackNavigator (clj->js route-configs) (clj->js navigator-config))))
(defn add-navigation-helpers [dispatch-fn nav-state]
  (addNavigationHelpers
    (clj->js
      {"dispatch" (fn add-navigation-helpers-dispatch [a]
                    (dispatch-fn a))
       "state"    (clj->js nav-state)})))

(defn make-drawer-navigator [drawer-router drawer-navigator-config]
  (DrawerNavigator (clj->js drawer-router) (clj->js drawer-navigator-config)))

(defn make-tab-navigator [tab-router tab-navigator-config]
  (TabNavigator (clj->js tab-router) (clj->js tab-navigator-config)))

(defn navigator-state-for-action [navigator action]
  (getStateForAction (getRouter navigator) action))

(defn navigator-action-for-path-and-params [navigator path params]
  (getActionForPathAndParams (getRouter navigator) path params))

(defn navigator-path-and-params-for-state [navigator state]
  (getPathAndParamsForState (getRouter navigator) state))

(defn navigator-component-for-state [navigator state]
  (getComponentForState (getRouter navigator) state))

(defn navigator-component-for-route-name [navigator route-name]
  (getComponentForRouteName (getRouter navigator) route-name))

(defn navigation-actions-back [] (back))
(defn navigation-actions-init [] (init))
(defn navigation-actions-navigate [a] (navigate a))
(defn navigation-actions-reset [] (reset))
(defn navigation-actions-setParams [] (setParams))
(defn navigation-actions-uri [] (uri))

(defn navigation->state [navigation]
  (-> navigation .-state (js->clj :keywordize-keys true)))

(defn wrap-navigation [component {:keys [title
                                         left
                                         right
                                         tab-label
                                         tab-icon
                                         background-color]
                                  :or {title (fn wrap-navigation-title-fn [a b] (str "title" a))
                                       left nil
                                       right nil
                                       tab-label nil
                                       tab-icon nil
                                       background-color "#61B2E9"}}]
  (let [c (r/reactify-component
            (fn wrap-navigation-r [{:keys [navigation]}]
              [component (navigation->state navigation)]))]
    (aset c "navigationOptions"
          (fn wrap-navigation-fn [navigation]
            (clj->js (merge {; :tabBar (fn wrap-navigation-tab-bar-fn [navigation]
                             ;           (let [state (navigation->state navigation)]
                             ;            (clj->js
                             ;              {:label (if tab-label (tab-label state) "-EMPTY-")})))
                             ;               ;:icon (if tab-icon (tab-icon state) nil)})))
                             :title   (let [state (navigation->state navigation)
                                            {:keys [params routeName]} state]
                                          (if title (title state) nil))
                             :headerTintColor "#FFF"
                             :headerTitleStyle {:color "#FFF"
                                                :marginLeft 30}
                             :headerStyle {:backgroundColor background-color}}
                            (if left {:headerLeft (left (navigation->state navigation))} {})
                            (if right {:headerRight (right (navigation->state navigation))} {})))))
    c))
